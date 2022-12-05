package com.ihrm.audit.client;

import com.ihrm.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 声明接口，通过feign调用其他微服务
 *
 * @author Administrator
 */
@FeignClient("ihrm-system")
public interface SystemFeignClient {


    /**
     * 查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @RequestMapping(value = "/sys/user/{id}", method = RequestMethod.GET)
    Result findById(@PathVariable(value = "id") String id);
}
