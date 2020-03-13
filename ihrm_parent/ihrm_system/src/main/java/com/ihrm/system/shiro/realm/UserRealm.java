package com.ihrm.system.shiro.realm;

import com.ihrm.common.shiro.realm.IhrmRealm;
import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.ProfileResult;
import com.ihrm.system.service.PermissionService;
import com.ihrm.system.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if ("user".equals(user.getLevel())){
                result = new ProfileResult(user);
            }else{
                Map map = new HashMap();
                if ("coAdmin".equals(user.getLevel())){
                    map.put("enVisible" , "1");
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
