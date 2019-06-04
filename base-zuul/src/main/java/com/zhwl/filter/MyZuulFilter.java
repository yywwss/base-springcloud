package com.zhwl.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 利用网关过滤无效url
 */
@Component
public class MyZuulFilter extends ZuulFilter {

    //是否开启过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //何时执行该过滤器
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        String requestURL = request.getRequestURL().toString();
        System.out.println(requestURI);
        System.out.println(requestURL);
        /*if(requestURI.startsWith("/api-stock") || requestURI.startsWith("/api-order"))
            return null;

        //直接相应结果给客户端，不会调用服务接口
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(404);
        currentContext.setResponseBody("页面找不到");*/
        return null;
    }

}
