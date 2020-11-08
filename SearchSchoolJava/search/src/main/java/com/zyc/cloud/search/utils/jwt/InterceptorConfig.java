package com.zyc.cloud.search.utils.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chen
 * @version 1.0
 * @date 2020/10/29 17:06
 */
//有关拦截器的配置
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //指定拦截器并添加相应策略(拦截或放行）
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/userLogin/test")
                .excludePathPatterns("userLogin/login");
    }
}