package cn.itcast.shiro.realm;

import cn.itcast.shiro.domain.Permission;
import cn.itcast.shiro.domain.Role;
import cn.itcast.shiro.domain.User;
import cn.itcast.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: hyl
 * @date: 2020/02/04
 **/
public class CustomRealm extends AuthorizingRealm {

    public void setName(String name){
        super.setName("customRealm");
    }

    @Autowired
    private UserService userService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.获取已认证的用户数据
        User user = (User) principalCollection.getPrimaryPrincipal();
        //2.根据用户数据获取用户的权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();//所有角色
        Set<String> perms = new HashSet<>();//所有权限
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
            for (Permission perm : role.getPermissions()) {
                perms.add(perm.getCode());
            }
        }
        info.setStringPermissions(perms);
        info.setRoles(roles);
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取登录的用户名和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //2.根据用户名查询数据库
        User user = userService.findByName(username);
        //3.判断用户是否存在或者密码是否一致
        if (user != null && user.getPassword().equals(password)){
            //4.如果一致返回安全数据
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user , user.getPassword() , this.getName());
            return info;
        }
        //5.不一致,返回null(抛出异常)
        
        return null;
    }
}
