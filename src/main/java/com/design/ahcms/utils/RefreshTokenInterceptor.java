package com.design.ahcms.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.design.ahcms.common.UserHolder;
import com.design.ahcms.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;
import java.util.concurrent.TimeUnit;
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;
    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1。获取session
//        HttpSession session = request.getSession();
        //1. 从请求头获取token
        String token = request.getHeader("authorization");

        log.info("token:"+token);
        if(StrUtil.isBlank(token)){
            //response.setStatus(401);
            return true;
        }
        //2。从redis中获取用户
        String key=RedisConstants.LOGIN_TOKEN + token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
        //3. 刷新token有效期
        //2.获取session中的用户
//        Object user=session.getAttribute("user");
        //3.判断是否存在
        if(userMap.isEmpty()){

            return true;
        }
        UserDto userDto = BeanUtil.fillBeanWithMap(userMap, new UserDto(), false);
        log.info("hloder1"+userDto.toString());
        UserHolder.saveUser(userDto);
        stringRedisTemplate.expire(key,RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        //4.不存在，拦截
        return true;
    }
}
