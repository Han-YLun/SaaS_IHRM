package com.ihrm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: hyl
 * @date: 2020/01/04
 **/
public class BaseController {

    public HttpServletRequest request;
    public HttpServletResponse response;
    protected String companyId;
    protected String companyName;

    //进入控制器之前执行的方法
    @ModelAttribute
    public void serResAndReq(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.companyId = "1";
        this.companyName = "传智播客";
    }
}
