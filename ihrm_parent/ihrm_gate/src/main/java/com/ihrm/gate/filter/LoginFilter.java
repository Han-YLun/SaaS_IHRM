package com.ihrm.gate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义过滤器
 * @author: hyl
 * @date: 2020/03/13
 **/
//@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 定义过滤器类型
     *      pre     ：   在执行路由请求之前执行
     *      routing ：   在路由请求是调用
     *      post    ：   在routing和error过滤器之后执行
     *      error   ：   处理请求出现异常的时候执行
     * @return  返回过滤器类型
     */
    @Override
    public String filterType() {
        return "pre";
    }


    /**
     * 定义过滤器的优先级    ：   数字越小,优先级越高
     * @return  过滤器的优先级
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 判断过滤器是否需要执行
     * @return  过滤器是否需要执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器中负责的具体业务逻辑
     * @return  返回NULL,继续向后执行
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //登录校验逻辑
        //获取Zuul提供的请求上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文中获取request对象
        HttpServletRequest req = ctx.getRequest();
        //从请求中获取token
        String token = req.getHeader("Authorization");
        //判断
        if(token == null || "".equals(token.trim())){
            //没有token，登录校验失败，拦截
            ctx.setSendZuulResponse(false);
            //返回401状态码。也可以考虑重定向到登录页。
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        //校验通过，可以考虑把用户信息放入上下文，继续向后执行
        return null;
    }
}
