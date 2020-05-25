package com.ihrm.system.service;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.BeanMapUtils;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.common.utils.PermissionConstants;
import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.PermissionApi;
import com.ihrm.domain.system.PermissionMenu;
import com.ihrm.domain.system.PermissionPoint;
import com.ihrm.system.dao.PermissionApiDao;
import com.ihrm.system.dao.PermissionDao;
import com.ihrm.system.dao.PermissionMenuDao;
import com.ihrm.system.dao.PermissionPointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/01/09
 **/
@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionMenuDao permissionMenuDao;

    @Autowired
    private PermissionPointDao permissionPointDao;
    
    @Autowired
    private PermissionApiDao permissionApiDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存权限
     */
    public void save(Map<String,Object> map) throws Exception {
        //设置主键
        String id = idWorker.nextId() + "";
        //通过map构造权限对象
        Permission perm = BeanMapUtils.mapToBean(map , Permission.class);
        perm.setId(id);
        //根据类型构造不同的资源对象(菜单,按钮,api)
        int type = perm.getType();
        switch (type){
            case PermissionConstants.PY_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                menu.setId(id);
                permissionMenuDao.save(menu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                point.setId(id);
                permissionPointDao.save(point);
                break;
            case PermissionConstants.PY_API:
                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
                api.setId(id);
                permissionApiDao.save(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }

        //保存权限
        permissionDao.save(perm);
    }

    /**
     * 更新
     */
    public void update(Map<String,Object> map) throws Exception {

        Permission perm = BeanMapUtils.mapToBean(map , Permission.class);
        //通过传递的权限id查询权限
        Permission permission = permissionDao.findById(perm.getId()).get();
        permission.setName(perm.getName());
        permission.setDescription(perm.getDescription());
        perm.setEnVisible(perm.getEnVisible());
        //根据类型构造不同的资源
        int type = perm.getType();
        switch (type){
            case PermissionConstants.PY_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                menu.setId(perm.getId());
                permissionMenuDao.save(menu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                point.setId(perm.getId());
                permissionPointDao.save(point);
                break;
            case PermissionConstants.PY_API:
                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
                api.setId(perm.getId());
                permissionApiDao.save(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //更新权限,更新资源
        permissionDao.save(permission);
    }


    /**
     * 根据id查询权限
     */
    public Map findById(String id) throws CommonException {
        //查询权限
        Permission perm = permissionDao.findById(id).get();
        //根据权限的类型查询资源
        int type = perm.getType();
        //构造map集合
        Object object = null;

        if (type == PermissionConstants.PY_MENU){
            object = permissionMenuDao.findById(id).get();
        }else if (type == PermissionConstants.PY_POINT){
            object = permissionPointDao.findById(id).get();
        }else if (type == PermissionConstants.PY_API){
            object = permissionApiDao.findById(id).get();
        }else{
            throw new CommonException(ResultCode.FAIL);
        }

        Map<String, Object> map = BeanMapUtils.beanToMap(object);
        map.put("name" , perm.getName());
        map.put("type" , perm.getType());
        map.put("code" , perm.getCode());
        map.put("description" , perm.getDescription());
        map.put("pid" , perm.getPid());
        map.put("enVisible" , perm.getEnVisible());

        return map;
    }


    /**
     * 查询全部用户列表
     * type     :   查询全部权限列表
     *          0 : 菜单 + 按钮(权限点)    1 ： 菜单  2 : 按钮(权限点) 3 ： API接口
     * enVisible :
     *          0 ： 查询SaaS平台的最高权限   1 ： 查询企业的权限
     */
    public List<Permission> findAll(Map<String,Object> map){

        //查询条件
        Specification<Permission> spec = new Specification<Permission>() {

            /**
             * 动态拼接查询条件
             * @param root
             * @param query
             * @param cb
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //根据父id查询
                if (!StringUtils.isEmpty(map.get("pid"))){
                     list.add(cb.equal(root.get("pid").as(String.class) , (String)map.get("pid")));
                }

                //根据enVisible查询
                if (!StringUtils.isEmpty(map.get("enVisible"))){
                    list.add(cb.equal(root.get("enVisible").as(String.class) , map.get("enVisible")));
                }

                //根据类型type进行查询
                if (!StringUtils.isEmpty(map.get("type"))){
                    String type = (String) map.get("type");
                    CriteriaBuilder.In<Object> in = cb.in(root.get("type"));

                    if ("0".equals(type)){
                        in.value(1).value(2);
                    }else {
                        in.value(Integer.parseInt(type));
                    }
                    list.add(in);
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };

        return permissionDao.findAll(spec);
    }

    /**
     * 根据id删除权限
     */
    public void deleteById(String id) throws CommonException {
        //通过传递的权限id查询权限
        Permission permission = permissionDao.findById(id).get();
        permissionDao.delete(permission);
        //根据类型构造不同的资源
        int type = permission.getType();
        switch (type){
            case PermissionConstants.PY_MENU:
                permissionMenuDao.deleteById(id);
                break;
            case PermissionConstants.PY_POINT:
                permissionPointDao.deleteById(id);
                break;
            case PermissionConstants.PY_API:
                permissionApiDao.deleteById(id);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
    }

    /**
     * 查询所有企业可以看到的menu
     */
    public List<Permission> getMenus() throws CommonException {
        List<Permission> pers = permissionDao.findByTypeAndEnVisible(1, "1");
        return pers;
    }
}

