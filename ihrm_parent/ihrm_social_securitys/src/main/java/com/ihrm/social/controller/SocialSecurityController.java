package com.ihrm.social.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.social_security.CompanySettings;
import com.ihrm.social.service.CompanySettingsService;
import com.ihrm.social.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/03/14
 **/
@RestController
@RequestMapping("/social_securitys")
public class SocialSecurityController extends BaseController {

    @Autowired
    private CompanySettingsService companySettingsService;

    @Autowired
    private UserSocialService userSocialService;

    /**
     * 查询企业是否设置过社保
     */
    @RequestMapping(value = "/settings" , method = RequestMethod.GET)
    public Result settings(){
        CompanySettings cs = companySettingsService.findById(companyId);
        return new Result(ResultCode.SUCCESS , cs);
    }

    /**
     * 保存企业设置
     */
    @RequestMapping(value = "/settings" , method = RequestMethod.POST)
    public Result saveSettings(@RequestBody CompanySettings companySettings){
        companySettings.setCompanyId(companyId);
        companySettingsService.save(companySettings);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业员工的社保信息列表
     */                       
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public Result list(@RequestBody Map map){
        //1.获取请求参数
        int page = (int) map.get("page");
        int pageSize = (int) map.get("pageSize");
        //2.调用service进行查询
        PageResult pr = userSocialService.findAll(page , pageSize , companyId);
        return new Result(ResultCode.SUCCESS , pr);
    }


}
