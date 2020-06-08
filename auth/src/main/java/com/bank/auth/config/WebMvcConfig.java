package com.bank.auth.config;

import com.bank.auth.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Bean
    public TokenInterceptor getTokenInterceptor(){
        return new TokenInterceptor();
    }


    /**
     * 注入自定义拦截器
     * @Title: addInterceptors
     * @Description: 先add的拦截器会越靠外，即越靠近浏览器
     * @Date 2020年6月2日
     * @author zhaozhongyuan
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(getTokenInterceptor())
                .addPathPatterns("/**")//拦截所有请求
                .excludePathPatterns("/","/ssoLogin","/userLogin"); //对应的不拦截
    }

}

