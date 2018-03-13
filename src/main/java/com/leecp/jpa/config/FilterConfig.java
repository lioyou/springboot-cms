package com.leecp.jpa.config;

import com.leecp.jpa.common.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        List<String> urlPatterns = new ArrayList<String>();

        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        //过滤指定的路径
        urlPatterns.add("/ucenter/*");
        urlPatterns.add("/admin/*");
        filterRegistrationBean.setFilter(authenticationFilter);
        filterRegistrationBean.setUrlPatterns(urlPatterns);

        return filterRegistrationBean;
    }
}
