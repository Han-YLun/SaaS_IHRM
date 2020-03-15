package com.ihrm.social.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.social_security.CityPaymentItem;
import com.ihrm.domain.social_security.CompanySettings;
import com.ihrm.domain.social_security.UserSocialSecurity;
import com.ihrm.social.client.SystemFeignClient;
import com.ihrm.social.service.CompanySettingsService;
import com.ihrm.social.service.PaymentItemService;
import com.ihrm.social.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private SystemFeignClient systemFeignClient;

    @Autowired
    private PaymentItemService paymentItemService;

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

    /**
     * 查询用户id查询用户的社保数据
     */
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        Map map = new HashMap();
        //1.根据用户id查询用户数据
        Object user = systemFeignClient.findById(id).getData();
        map.put("user" , user);
        //2.根据用户id查询社保数据
        UserSocialSecurity uss = userSocialService.findById(id);
        map.put("userSocialSecurity" , uss);
        return new Result(ResultCode.SUCCESS , map);
    }

    /**
     * 查询城市id查询城市的参保项目
     */
    @RequestMapping(value = "/payment_item/{id}" , method = RequestMethod.GET)
    public Result findPaymentItem(@PathVariable String id){
        List<CityPaymentItem> list = paymentItemService.findAllByCityId(id);
        return new Result(ResultCode.SUCCESS , list);
    }

    /**
     * 保存或更新用户社保
     */
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public Result saveUserSocialSecurity(@RequestBody UserSocialSecurity uss){
        userSocialService.save(uss);
        return new Result(ResultCode.SUCCESS);
    }


}
