package com.cwb.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //重写方法,传入自定义的拦截器对象,然后配置拦截路径,开放登录页面的访问权限
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
                                                       .excludePathPatterns("/admin")
                                                       .excludePathPatterns("/admin/login");

    }
}
