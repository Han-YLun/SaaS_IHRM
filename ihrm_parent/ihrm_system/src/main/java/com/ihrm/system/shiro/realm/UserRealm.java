package com.ihrm.system.shiro.realm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonObject;
import com.ihrm.common.Constants.Constant;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.shiro.realm.IhrmRealm;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.ProfileResult;
import com.ihrm.system.client.CompanyFeignClient;
import com.ihrm.system.service.PermissionService;
import com.ihrm.system.service.UserService;
import io.netty.util.internal.ObjectUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author: hyl
 * @date: 2020/02/08
 **/
public class UserRealm extends IhrmRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    //认证方法  
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {

        //获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //根据手机号查询用户
        User user = userService.findByMobile(mobile);

        //根据用户是否存在,用户密码是否和输入密码一致
        if (user != null && user.getPassword().equals(password)){
            //构造安全数据并返回(安全数据：用户基本信息,权限信息,ProfileResult)
            ProfileResult result = null;
            //如果是员工,就把员工的信息保存
            if (Constant.UserLevel.USER.equals(user.getLevel())){
                result = new ProfileResult(user);
            }else{
                Map map = new HashMap();
                //如果是企业管理员,就查询企业管理员可见的
                if (Constant.UserLevel.COADMIN.equals(user.getLevel())){
                    map.put("enVisible" , "1");
                }else if (Constant.UserLevel.SAASADMIN.equals(user.getLevel())){
                    //如果是SaaS管理员，只显示企业不显示的
                    /**
                     * 即只显示企业管理和模块管理
                     */
                    map.put("enVisible" , "0");
                }
                List<Permission> list = permissionService.findAll(map);
                result = new ProfileResult(user , list);
            }

            //构造方法：安全数据,密码,realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result , user.getPassword() , this.getName());
            return info;
        }
        //返回null,会抛出异常,表示用户名和密码不匹配
        return null;
    }

    
}
