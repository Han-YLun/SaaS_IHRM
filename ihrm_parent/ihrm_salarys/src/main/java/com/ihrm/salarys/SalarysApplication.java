package com.ihrm.salarys;

import com.ihrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.ihrm")
@EntityScan(value = "com.ihrm.domain")
@EnableFeignClients
public class SalarysApplication {
    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(SalarysApplication.class, args);
    }


    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
