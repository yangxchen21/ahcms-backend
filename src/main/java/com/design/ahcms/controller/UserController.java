package com.design.ahcms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import com.design.ahcms.dto.RegisterDto;
import com.design.ahcms.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param registerDto 注册信息
     * @return Result
     */
    @PostMapping
    public Result<String> save(@RequestBody RegisterDto registerDto){
        userService.register(registerDto);
        return Result.success("注册成功");
    }
    @GetMapping("/login")
    public Result<User> login(String username,String password){
        User user = userService.getUserByName(username);
        if(Objects.equals(user.getPassword(), password)){
            StpUtil.login(user.getId());


            return Result.success(user);
        }
        return Result.error(500,"登录失败");
    }
    @PostMapping("/logout")
    public Result<String> logout(){
        StpUtil.logout();
        return Result.success("注销成功");
    }
}
