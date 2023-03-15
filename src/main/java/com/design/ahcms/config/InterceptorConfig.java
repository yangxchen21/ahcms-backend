package com.design.ahcms.config;

import com.design.ahcms.utils.LoginInterceptor;
import com.design.ahcms.utils.RefreshTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource(value = "classpath:application.yml")
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Value("${img.path}")
    private String basePath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(
                "/article-type/**"

        ).excludePathPatterns(
                "/user/code**",
                "/user/login",
                "/user",
                "/school",
                "/department/**",
                "/article/**",
                "/img/**"

        );


    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("http:\\127.0.0.1:8887\\")
//                .addResourceLocations("file:D:\\ahcmstore\\");
//    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        /*
//         * 资源映射路径
//         * addResourceHandler:访问映射路径
//         * addResourceLocations:资源绝对路径
//         */
//        registry.addResourceHandler("/image/**").addResourceLocations("file:"+basePath);
//    }

}
