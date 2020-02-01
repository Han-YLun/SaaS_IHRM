package com.ihrm.common.interceptor;

import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @author: hyl
 * @date: 2020/02/01
 **/
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;


    /**
     * 简化获取token数据的代码编写(判断是否登录)
     *  1.通过request获取请求token信息
     *  2.从token中解析获取claims
     *  3.将claims绑定到request域中
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.通过request获取请求token信息
        String authorization = request.getHeader("Authorization");
        //判断请求头信息是否为空,或者是否以Bearer开头
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")){
            //获取token数据
            String token = authorization.replace("Bearer ","");
            //从token中解析获取claims
            Claims claims = jwtUtils.parseJwt(token);
            if (claims != null){
                request.setAttribute("user_claims" , claims);
                return true;
            }
        }

        throw new CommonException(ResultCode.UNAUTHENTICATED);
    }

}
