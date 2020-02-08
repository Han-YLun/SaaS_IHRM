package cn.itcast.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: hyl
 * @date: 2020/02/03
 **/
public class PermissionRealm extends AuthorizingRealm {

    /**
     * 自定义realm名称
     * @param name
     */
    public void setName(String name){
        super.setName("permissionRealm");
    }

    //授权:授权的目的就是根据认证数据获取到用户的权限信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.获取安全数据 username,password
        String username = (String) principalCollection.getPrimaryPrincipal();
        //2.根据id或者名称查询用户
        //3.查询用户的角色和权限信息
        List<String> perms = new ArrayList<String>();
        perms.add("user:save");
        perms.add("user:update");
        
        List<String> roles = new ArrayList<String>();
        roles.add("role1");
        roles.add("role2");
        //4.构造返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置权限集合
        info.addStringPermissions(perms);
        //设置角色集合
        info.addRoles(roles);
        return info;
    }

    //认证:认证的主要目的,比较y用户和密码是否与数据库中的一致
    //将安全数据存到shiro进行保管
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //构造uptoken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //获取输入的用户名和密码
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //根据用户名查询数据库,正式系统查询
        //比较密码和数据库中的密码是否一致(密码可能需要加密)
        if ("123456".equals(password)){
            //如果成功,向shiro存入安全数据
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username , password , getName());//安全数据,密码,当前realm域名称
            return info;
        }else{
            //如果失败,抛出异常或返回null
            throw new RuntimeException("用户名或密码错误");
        }
    }
}
