package com.ihrm.common.handler;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的公共异常处理器
 * 1.声明异常处理器
 * 2.对异常统一处理
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (e.getClass() == CommonException.class) {
            //类型转型
            CommonException ce = (CommonException) e;
            return new Result(ce.getResultCode());
        }
        return new Result(ResultCode.SERVER_ERROR);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) {
        return new Result(ResultCode.UNAUTHORISE);
    }
}
