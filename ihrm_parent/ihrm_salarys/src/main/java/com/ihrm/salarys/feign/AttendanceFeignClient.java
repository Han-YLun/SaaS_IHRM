package com.ihrm.salarys.feign;

import com.ihrm.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


//声明调用的微服务名称
@FeignClient("ihrm-attendance")
public interface AttendanceFeignClient {
    /**
     * 调用考勤模块的归档明细
     */
    @RequestMapping(value = "/attendances/archive/{userId}/{yearMonth}" , method = RequestMethod.GET)
    Result historyData(@PathVariable("userId") String userId, @PathVariable("yearMonth") String yearMonth);
}
