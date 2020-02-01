package com.ihrm.common.controller;

import io.jsonwebtoken.Claims;
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
    protected Claims claims;

    //进入控制器之前执行的方法
    @ModelAttribute
    public void serResAndReq(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;

        Object obj = (Claims) request.getAttribute("user_claims");
        if (obj != null){
            this.claims = (Claims) obj;
            this.companyId = (String) claims.get("companyId");
            this.companyName = (String) claims.get("companyName");
        }
    }
}
