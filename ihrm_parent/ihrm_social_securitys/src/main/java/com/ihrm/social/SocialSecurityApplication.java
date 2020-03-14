package com.ihrm.social;

import com.ihrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author: hyl
 * @date: 2020/01/05
 **/
@SpringBootApplication(scanBasePackages = "com.ihrm")
@EntityScan(value="com.ihrm.domain")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class SocialSecurityApplication {


    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(SocialSecurityApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }


    //解决jpa no session问题
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
        return new OpenEntityManagerInViewFilter();
    }
}
