package cn.itcast.shiro;

import cn.itcast.shiro.realm.CustomRealm;
import cn.itcast.shiro.session.CustomSessionManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/02/04
 **/
@Configuration
public class ShiroConfiguration {

    //创建realm
    @Bean
    public CustomRealm getRealm(){
        return new CustomRealm();
    }

    //创建安全管理器
    @Bean
    public SecurityManager getSecurityManager(CustomRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);

        //将自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(sessionManager());
        //将自定义的redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    //配置shiro的过滤器工厂
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //1.创建过滤工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        filterFactory.setSecurityManager(securityManager);
        //3.通用配置
        filterFactory.setLoginUrl("/autherror?code=1");
        filterFactory.setUnauthorizedUrl("/autherror?code=2");
        //4.设置过滤器集合

        Map<String,String> filterMap = new LinkedHashMap<>();
        //filterMap.put("/user/home" , "anon");//当前请求地址可以匿名访问

        //具有某种权限才能访问(使用过滤器的形式配置请求地址的依赖权限)
        //filterMap.put("/user/home" , "perms[user-home]");

        //使用过滤器的形式配置请求地址的依赖角色
        //filterMap.put("/user/home" , "roles[系统管理员]");
        
        filterMap.put("/user/**" , "authc");//当前请求地址必须认证之后才可以访问

        filterFactory.setFilterChainDefinitionMap(filterMap);
        
        return filterFactory;
    }

    //配置shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int  port;

    /**
     * redis的控制器,操作redis
     */
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }                                      

    /**
     * sessionDao
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }


    /**
     * 会话管理器
     */
    public DefaultWebSessionManager sessionManager(){
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 缓存管理器
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

}
