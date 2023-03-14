package com.design.ahcms.config;

import com.design.ahcms.utils.LoginInterceptor;
import com.design.ahcms.utils.RefreshTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(
                "/article-type/**"

                ).excludePathPatterns(
                        "/user/code**" ,
                    "/user/login",
                    "/user",
                    "/school",
                    "/department/**",
                    "/article/**",
                "/img/**"

        );

    }
}
