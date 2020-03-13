package com.ihrm.system.service;

import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.common.utils.PermissionConstants;
import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.Role;
import com.ihrm.system.dao.PermissionDao;
import com.ihrm.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: hyl
 * @date: 2020/01/15
 **/
@Service
public class RoleService extends BaseService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;


    /**
     * 添加角色
     */
    public void save(Role role){
        role.setId(idWorker.nextId() + "");
        roleDao.save(role);
    }

    /**
     * 更新角色
     */
    public void update(Role role){
        Role target = roleDao.getOne(role.getId());
        target.setDescription(role.getDescription());
        target.setName(role.getName());
        roleDao.save(target);
    }

    /**
     * 根据ID查询角色
     */
    public Role findById(String id){
        return roleDao.findById(id).get();
    }

    /**
     * 查询所有角色:
     *      根据内部维护的公司id进行查询该公司的所有角色
     */
    public List<Role> findAll(String companyId){
        return roleDao.findAll(getSpec(companyId));
    }

    /**
     * 根据id删除角色
     */
    public void delete(String id){
        roleDao.deleteById(id);
    }

    /**
     * 获取分页角色集合
     * @param companyId 公司id
     * @param page  第几页
     * @param size  每页大小
     * @return  角色集合
     */
    public Page<Role> findByPage(String companyId, int page, int size){
        return roleDao.findAll(getSpec(companyId) , PageRequest.of(page-1 , size));
    }

    /**
     * 分配权限
     * @param roleId    角色id
     * @param permIds   权限id列表
     */
    public void assignPerms(String roleId, List<String> permIds) {
        //获取分配的角色对象
        Role role = roleDao.findById(roleId).get();
        //构造角色的权限集合
        Set<Permission> perms = new HashSet<>();
        for (String permId : permIds) {
            Permission perm = permissionDao.findById(permId).get();
            //需要根据父id和类型查询API权限列表
            List<Permission> apiList = permissionDao.findByTypeAndPid(PermissionConstants.PY_API, perm.getId());
            perms.addAll(apiList);//自动赋予API权限
            perms.add(perm);//当前菜单或按钮的权限
        }
        //设置角色和权限的关系
        role.setPermissions(perms);
        //更新角色
        roleDao.save(role);
    }
}
