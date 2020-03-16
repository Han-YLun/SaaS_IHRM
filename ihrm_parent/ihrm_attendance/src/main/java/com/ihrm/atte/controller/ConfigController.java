package com.ihrm.atte.controller;

import com.ihrm.atte.dao.AttendanceDao;
import com.ihrm.atte.service.ConfigurationService;
import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.atte.entity.AttendanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设置考勤设置的controller
 * @author: hyl
 * @date: 2020/03/16
 **/
@RestController
@RequestMapping("/cfg")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigurationService configurationService;

    /**
     * 获取考勤设置
     */
    @RequestMapping(value = "/atte/item" , method = RequestMethod.POST)
    public Result atteConfig(String departmentId){
        AttendanceConfig ac = configurationService.getAtteConfig(companyId , departmentId);
        return new Result(ResultCode.SUCCESS , ac);
    }

    /**
     * 保存考勤设置
     */
    @RequestMapping(value = "/atte" , method = RequestMethod.PUT)
    public Result saveAtteConfig(@RequestBody  AttendanceConfig ac){
        ac.setCompanyId(companyId );
        configurationService.saveAtteConfig(ac);
        return new Result(ResultCode.SUCCESS);
    }

}
