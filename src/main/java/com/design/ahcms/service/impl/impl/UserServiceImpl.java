package com.design.ahcms.service.impl.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.design.ahcms.common.Result;
import com.design.ahcms.common.ResultEnum;
import com.design.ahcms.common.UserHolder;
import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import com.design.ahcms.dto.LoginDto;
import com.design.ahcms.dto.RegisterDto;
import com.design.ahcms.dto.UserDto;
import com.design.ahcms.mapper.UserMapper;
import com.design.ahcms.service.impl.UserService;
import com.design.ahcms.utils.RedisConstants;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override

    public void register(RegisterDto registerDto) {
        UserDetail userDetail = new UserDetail();
        userDetail.setName(registerDto.getName());
        userDetail.setEmail(registerDto.getEmail());
        userDetail.setIdcardNumber(registerDto.getIdcardNumber());
        userDetail.setAddress(registerDto.getAddress());
        userDetail.setDepartmentId(registerDto.getDepartmentId());
        userDetail.setType(registerDto.getType());
        userMapper.saveUserDetail(userDetail);
        ((User) registerDto).setDetailId(userDetail.getId());
        userMapper.saveUserPassword((User) registerDto);
    }


    @Override
    public void code(String phone, HttpSession session) {
        //1.校验手机号

        //2.不符合返回错误信息

        //3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);
        //4.保存到session
        session.setAttribute("code", code);
        //发送验证码（这里模拟）
        log.info("code------" + code);
        //保存验证码到redis
        stringRedisTemplate.opsForValue().set(RedisConstants.LOGIN_CODE_KEY + phone, code, RedisConstants.LOGIN_CODE_TTL, TimeUnit.MINUTES);
    }

    @Override
    public Result<UserDto> loginByCode(LoginDto loginDto, HttpSession session) {
        //1.校验手机号
        //2.校验验证码
//        Object cacheCode=session.getAttribute("code");

        Object cacheCode = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_CODE_KEY + loginDto.getPhone());
        //3.不一致保存
        String code = loginDto.getCode();
        if (cacheCode == null || !cacheCode.toString().equals(code)) {
            return Result.error(ResultEnum.FAIL.getCode(), "验证码错误");
        }
        //4.根据手机号查用户
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getPhone, loginDto.getPhone());
        User user = this.getOne(lambdaQueryWrapper);
        //5.判断用户是否存在
        if (user == null) {
            return Result.error(ResultEnum.FAIL.getCode(), "用户不存在");
        }
        //6.不存在，报错
        //7.存在，保存用户信息到session
//        session.setAttribute("user_"+user.getId(),user);
        //保存到redis中
        //1.生成token作为登录令牌
        String token = UUID.randomUUID().toString(true);
        //2.将user对象转为hash存储
        UserDto userDto = BeanUtil.copyProperties(user, UserDto.class);
        userDto.setToken(token);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", userDto.getId().toString());
        userMap.put("phone", userDto.getPhone());
        userMap.put("username", userDto.getUsername());
        userMap.put("status", userDto.getStatus().toString());
        String key = RedisConstants.LOGIN_TOKEN + token;
        stringRedisTemplate.opsForHash().putAll(key, userMap);
        UserHolder.saveUser(userDto);
        stringRedisTemplate.expire(key, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);


        return Result.success(userDto);
    }

    @Override
    public Result<UserDto> loginByUsername(String username, String password) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            return Result.error(ResultEnum.FAIL.getCode(), "用户不存在");
        }
        if (!user.getPassword().equals(password))  {
            return Result.error(ResultEnum.FAIL.getCode(), "密码错误");
        }
        //1.生成token作为登录令牌
        String token = UUID.randomUUID().toString(true);
        //2.将user对象转为hash存储
        UserDto userDto = BeanUtil.copyProperties(user, UserDto.class);
        userDto.setToken(token);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", userDto.getId().toString());
        userMap.put("phone", userDto.getPhone());
        userMap.put("username", userDto.getUsername());
        userMap.put("status", userDto.getStatus().toString());
        String key = RedisConstants.LOGIN_TOKEN + token;
        stringRedisTemplate.opsForHash().putAll(key, userMap);
        UserHolder.saveUser(userDto);
        stringRedisTemplate.expire(key, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);


        return Result.success(userDto);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        String key = RedisConstants.LOGIN_TOKEN + token;
        UserHolder.removeUser();
        stringRedisTemplate.delete(key);
    }


}
