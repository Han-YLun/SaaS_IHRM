package com.ihrm.atte;

import com.ihrm.common.shiro.realm.IhrmRealm;
import com.ihrm.common.shiro.session.CustomSessionManager;
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
 * @author arvinyl
 */
@Configuration(value = "ihrm_attendance_shiroConfiguration")
public class ShiroConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    /**
     * 创建realm
     *
     * @return IhrmRealm
     */
    @Bean
    public IhrmRealm getRealm() {
        return new IhrmRealm();
    }

    /**
     * 创建安全管理器
     *
     * @param realm IhrmRealm
     * @return SecurityManager
     */
    @Bean
    public SecurityManager getSecurityManager(IhrmRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        //将自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(sessionManager());
        //将自定义的redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 配置shiro的过滤器工厂
     * 再web程序中，shiro进行权限控制全部是通过一组过滤器集合进行控制
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //1.创建过滤器工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        filterFactory.setSecurityManager(securityManager);
        //3.通用配置（跳转登录页面，未授权跳转的页面）
        //跳转url地址
        filterFactory.setLoginUrl("/autherror?code=1");
        //未授权的url
        filterFactory.setUnauthorizedUrl("/autherror?code=2");
        //4.设置过滤器集合
        Map<String, String> filterMap = new LinkedHashMap<>();
        //anon -- 匿名访问
        filterMap.put("/sys/login", "anon");
        filterMap.put("/sys/city/**", "anon");
        filterMap.put("/sys/faceLogin/**", "anon");
        filterMap.put("/autherror", "anon");
        //注册
        //authc -- 认证之后访问（登录）
        filterMap.put("/**", "authc");
        //perms -- 具有某中权限 (使用注解配置授权)
        filterFactory.setFilterChainDefinitionMap(filterMap);
        return filterFactory;
    }

    /**
     * redis的控制器，操作redis
     *
     * @return RedisManager
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        return redisManager;
    }

    /**
     * set sessionDao
     *
     * @return RedisSessionDAO
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    /**
     * 会话管理器
     *
     * @return DefaultWebSessionManager
     */
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 缓存管理器
     *
     * @return 缓存管理器
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 开启对shiro注解的支持
     *
     * @param securityManager 安全管理器
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
