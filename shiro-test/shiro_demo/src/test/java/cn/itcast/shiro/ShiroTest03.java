package cn.itcast.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: hyl
 * @date: 2020/02/03
 **/
public class ShiroTest03 {

    private SecurityManager securityManager;

    @Before
    public void init(){
        //1.根据配置文件创建SecurityManagerFactory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test-3.ini");
        //2.通过工厂获取SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //3.将SecurityManager绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
    }


    @Test
    public void testLogin(){
        Subject subject = SecurityUtils.getSubject();
        String username = "zhangsan";
        String password = "123456";
        UsernamePasswordToken token = new UsernamePasswordToken(username , password);
        
        subject.login(token);

        //登录成功之后,完全授权
        //授权:检验当前登录用户是否具有操作权限,是否具有某个角色
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:save"));
    }
}
