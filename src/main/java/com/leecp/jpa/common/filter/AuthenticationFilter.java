package com.leecp.jpa.common.filter;

import com.leecp.jpa.common.util.TokenAuthenticationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户是否已经认证
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        System.out.println("the url is : " + request.getQueryString());
        //判断是否拥有Token
        boolean authenticated = TokenAuthenticationUtil.parseToken(request);
        if(authenticated){//已经验证，继续执行
            filterChain.doFilter(request,response);
        }else{//跳转到登录页面
            response.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }
}
