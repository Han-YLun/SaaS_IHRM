package com.ihrm.system.client;

import com.ihrm.common.entity.Result;
import com.ihrm.domain.company.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

/**
 * 声明接口,通过feign调用其他微服务
 * @author: hyl
 * @date: 2020/02/11
 **/
@FeignClient("ihrm-company")
public interface CompanyFeignClient {

    /**
     * 调用微服务的接口
     */

    @RequestMapping(value = "/company/department/search" , method = RequestMethod.POST)
    Department findByCode(@RequestParam("code") String code, @RequestParam("companyId") String companyId);

    @RequestMapping(value = "/company/{id}" , method = RequestMethod.GET)
    Result findCompanyById(@PathVariable(value="id") String id);
}
