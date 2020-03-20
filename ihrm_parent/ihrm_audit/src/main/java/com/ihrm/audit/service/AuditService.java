package com.ihrm.audit.service;

import com.alibaba.fastjson.JSON;
import com.ihrm.audit.client.FeignClientService;
import com.ihrm.audit.dao.ProcInstanceDao;
import com.ihrm.audit.entity.ProcInstance;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.User;
import com.netflix.discovery.converters.Auto;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.hibernate.query.criteria.internal.predicate.NegatedPredicateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author: hyl
 * @date: 2020/03/20
 **/
@Service
public class AuditService {

    @Autowired
    private ProcInstanceDao procInstanceDao;

    @Autowired
    private FeignClientService feignClientService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

   /**
     * 查询所有的业务申请流程
     * @param instance  流程实例
     * @param page  页码
     * @param size  每页大小
     * @return
     */
    public Page getInstanceList(ProcInstance instance, int page, int size) {
        //使用Specification进行查询
        Specification<ProcInstance> spec = new Specification<ProcInstance>() {
            @Override
            public Predicate toPredicate(Root<ProcInstance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //审批类型
                if (!StringUtils.isEmpty(instance.getProcessKey())){
                    list.add(cb.equal(root.get("processKey").as(String.class) , instance.getProcessKey()));
                }
                //审批状态
                if (!StringUtils.isEmpty(instance.getProcessState())){
                    Expression<String> exp =root.get("processState");
                    list.add(exp.in(instance.getProcessState().split(",")));
                }
                //当前节点的待处理人
                if (!StringUtils.isEmpty(instance.getProcCurrNodeUserId())){
                    list.add(cb.like(root.get("procCurrNodeUserId").as(String.class) , "%" + instance.getProcCurrNodeUserId() + "%"));
                }
                //发起人 -- userId
                if(!StringUtils.isEmpty(instance.getUserId())) {
                    list.add(cb.equal(root.get("userId").as(String.class),instance.getUserId()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        //构造查询条件
        //调用dao进行specification查询
        return procInstanceDao.findAll(spec , new PageRequest(page-1 , size));
    }

    /**
     * 根据id查询申请的详细数据
     * @param id    流程实例id
     * @return
     */
    public ProcInstance findInstanceDetail(String id) {
       return procInstanceDao.findById(id).get();
    }

    /**
     * 流程申请
     * @param map
     * @param companyId
     * @return
     */
    public ProcInstance startProcess(Map map, String companyId) {
        //构造业务数据
        String userId = (String) map.get("userId");
        String processKey = (String) map.get("processKey");
        String processName = (String) map.get("processName");

        User user = feignClientService.getUserInfoByUserId(userId);
        ProcInstance instance = new ProcInstance();
        BeanUtils.copyProperties(user , instance);

        instance.setUserId(userId);
        instance.setProcessId(idWorker.nextId()+"");
        instance.setProcApplyTime(new Date());
        instance.setProcessKey(processKey);
        instance.setProcessName(processName);
        instance.setProcessState("1");  //审批中
        String data = JSON.toJSONString(map);
        instance.setProcData(data);
        //查询流程定义
        ProcessDefinition result = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processKey)
                .processDefinitionTenantId(companyId)
                .latestVersion()
                .singleResult();
        //开启流程
        Map vars = new HashMap();
        if ("process_leave".equals(processKey)){
            //请假
            vars.put("days" , map.get("duration"));
        }
        runtimeService.startProcessInstanceById(result.getId()
                , instance.getProcessId()
                , vars);  //流程定义的id,业务数据id,内置的参数
        //自动执行第一个任务节点

        //获取下一个节点数据,填充业务数据中当前待审批人
    }
}
