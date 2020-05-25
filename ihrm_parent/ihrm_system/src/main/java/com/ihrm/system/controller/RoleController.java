package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.system.Role;
import com.ihrm.domain.system.RoleAndUserRelations;
import com.ihrm.domain.system.response.RoleResult;
import com.ihrm.system.service.RoleService;
import com.ihrm.system.service.UserAndRoleRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/01/15
 **/

//解决跨域
@CrossOrigin
@RestController
@RequestMapping(value = "/sys")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAndRoleRelationsService userAndRoleRelationsService;

    /**
     * 分配权限
     */
    @RequestMapping(value = "/role/assignPrem" , method = RequestMethod.PUT)
    public Result save(@RequestBody Map<String,Object> map){

        //获取被分配的角色id
        String roleId = (String) map.get("id");
        //获取到权限的id列表
        List<String> permIds = (List<String>) map.get("permIds");
        //调用service完成权限分配
        roleService.assignPerms(roleId , permIds);

        return new Result(ResultCode.SUCCESS);
    }

    //添加角色
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Result add(@RequestBody Role role) throws Exception {
        role.setCompanyId(companyId);
        roleService.save(role);
        return Result.SUCCESS();
    }

    //更新角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Role role) throws Exception {
        roleService.update(role);
        return Result.SUCCESS();
    }

    //删除角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        roleService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 根据ID获取角色信息
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        Role role = roleService.findById(id);
        RoleResult roleResult = new RoleResult(role);
        return new Result(ResultCode.SUCCESS,roleResult);
    }

    /**
     * 分页查询角色
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result findByPage(int page,int pagesize,Role role) throws Exception {
        Page<Role> searchPage = roleService.findByPage(companyId, page, pagesize);
        PageResult<Role> pr = new PageResult(searchPage.getTotalElements(),searchPage.getContent());
        return new Result(ResultCode.SUCCESS,pr);
    }

    @RequestMapping(value="/role/list" ,method=RequestMethod.GET)
    public Result findAll() throws Exception {
        List<Role> roleList = roleService.findAll(companyId);
        return new Result(ResultCode.SUCCESS,roleList);
    }

    /**
     * 根据用户id获取全部权限
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/userId/{id}", method = RequestMethod.GET)
    public Result findRolesByUserId(@PathVariable(name = "id") String id) {
        List<RoleAndUserRelations> roleByUserId = userAndRoleRelationsService.findRoleByUserId(id);
        if (!ObjectUtils.isEmpty(roleByUserId)){
            List<Role> roles = userAndRoleRelationsService.getRoleDetailByRoleId(roleByUserId);
            if (!ObjectUtils.isEmpty(roles)){
                return new Result(ResultCode.SUCCESS , roles);
            }
        }
        return new Result(ResultCode.SUCCESS);
    }

}
