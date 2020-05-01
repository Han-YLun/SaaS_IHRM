package com.ihrm.system.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.domain.system.Permission;
import com.ihrm.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    /**
     * 保存
     * @return
     */
    @RequestMapping(value = "/permission" , method = RequestMethod.POST)
    public Result save(@RequestBody Map<String,Object> map) throws Exception {
        permissionService.save(map);
        return new Result(ResultCode.SUCCESS);
    }
    
    /**
     * 修改
     */
    @RequestMapping(value = "/permission/{id}" , method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id , @RequestBody Map<String,Object> map) throws Exception {
        //构造id
        map.put("id" , id);
        permissionService.update(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询列表
     * @return
     */
    @RequestMapping(value = "/permission" , method = RequestMethod.GET)
    public Result findAll(@RequestParam() Map map){
        List<Permission> list = permissionService.findAll(map);
        return new Result(ResultCode.SUCCESS , list);
    }


    /**
     * 根据Id查询
     */
    @RequestMapping(value = "/permission/{id}" , method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) throws CommonException {
        Map map = permissionService.findById(id);
        return new Result(ResultCode.SUCCESS , map);
    }



    /**
     * 根据Id删除
     */
    @RequestMapping(value = "/permission/{id}" , method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) throws CommonException {
        permissionService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询所有企业可以看到的menu
     */
    @RequestMapping(value = "/perm/getMenus" , method = RequestMethod.GET)
    public Result getMenus() throws CommonException {
        List<Permission> menus = permissionService.getMenus();
        return new Result(ResultCode.SUCCESS , menus);
    }


}
