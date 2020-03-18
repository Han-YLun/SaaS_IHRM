package com.ihrm.salarys.feign;

import com.ihrm.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//声明调用的微服务名称
@FeignClient("ihrm-social-securitys")
public interface SocialSecurityFeignClient {

    /**
     *  调用社保模块的归档明细
     */
    @RequestMapping(value = "/social_securitys/historys/archiveDetail/{userId}/{yearMonth}", method = RequestMethod.GET)
    Result historyData(@PathVariable("userId") String userId, @PathVariable("yearMonth") String yearMonth);
}
