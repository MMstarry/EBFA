package com.hujia.ebfa.config;

import com.hujia.ebfa.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author bin.lee
 * @date 2020/10/21 0021 15:34
 * @Email: libinjava@163.com
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/js/**","/img/**","/css/**","/fonts/**","/vendor/**");

    }

}
