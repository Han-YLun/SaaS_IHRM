package com.ihrm.company;


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
 * @author: hyl
 * @date: 2020/02/04
 **/
@Configuration(value = "ihrm_company")
public class ShiroConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int  port;

    //创建realm
    @Bean
    public IhrmRealm getRealm(){
        return new IhrmRealm();
    }

    //创建安全管理器
    @Bean
    public SecurityManager securityManager() {
        //使用默认的安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        //将自定义的realm交给安全管理器统一调度管理
        securityManager.setRealm(getRealm());
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

        //anno -- 匿名访问
        filterMap.put("/autherror" , "anon");
        //用于提供给feign使用
        filterMap.put("/company/**" , "anon");
        //authc -- 认证之后访问(登录)
        filterMap.put("/**" , "authc");

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
        //禁用cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //禁用url重写 url;jsessionId = id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
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
