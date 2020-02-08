package cn.itcast.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author: hyl
 * @date: 2020/02/03
 **/
public class ShiroTest01 {


    @Test
    public void testLogin(){
        //1.根据配置文件创建SecurityManagerFactory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test-1.ini");
        //2.通过工厂获取SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //3.将SecurityManager绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
        //4.从当前运行环境中获取subject
        Subject subject = SecurityUtils.getSubject();
        //5.构造shiro登录的数据
        String username = "zhangsan";
        String password = "123456";
        UsernamePasswordToken token = new UsernamePasswordToken(username , password);
        //6.主体登录
        subject.login(token);
        //7.验证用户是否登陆成功
        System.out.println("用户是否登陆成功=" + subject.isAuthenticated());
        //8.获取登录成功的数据
        System.out.println(subject.getPrincipal());
    }
}
