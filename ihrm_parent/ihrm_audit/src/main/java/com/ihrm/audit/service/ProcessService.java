package com.ihrm.audit.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: hyl
 * @date: 2020/03/19
 **/
@Service
public class ProcessService {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 进行流程部署
     * @param file  上传的bpmn文件
     * @param companyId 企业id
     */
    public void deployProcess(MultipartFile file, String companyId) throws IOException {
        //获取上传的文件名称
        String fileName = file.getOriginalFilename();
        //通过respositoryService进行流程部署
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addBytes(fileName , file.getBytes());   //部署流程
        deploymentBuilder.tenantId(companyId);
        Deployment deploy = deploymentBuilder.deploy();
        //打印部署结果
        System.out.println(deploy);
    }

    /**
     * 查询企业id对应的所有已部署流程
     * @param companyId 企业id
     * @return  企业id对应的所有已部署流程
     */
    public List getProcessDefinitionList(String companyId) {
        return repositoryService.createProcessDefinitionQuery()
                .processDefinitionTenantId(companyId).latestVersion().list();
    }

    /**
     *  挂起和恢复流程
     * @param processKey    流程的key
     */
    public void suspendProcess(String processKey) {
        ProcessDefinition definition =
                repositoryService.createProcessDefinitionQuery()
                        .processDefinitionKey(processKey).latestVersion().singleResult();
        if(definition.isSuspended()) { //如果是挂起状态，这里激活此流程
            repositoryService.activateProcessDefinitionById(definition.getId());
        }else{
//如果不是挂起状态，这里挂起此流程
            repositoryService.suspendProcessDefinitionById(definition.getId());
        }
    }
}
