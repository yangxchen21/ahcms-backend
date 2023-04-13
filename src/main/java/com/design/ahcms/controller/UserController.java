package com.design.ahcms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import com.design.ahcms.dto.LoginDto;
import com.design.ahcms.dto.RegisterDto;
import com.design.ahcms.dto.UserDto;
import com.design.ahcms.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.Objects;

@Slf4j
@RestController

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
    @PostMapping("/code")
    public Result<String> code(@RequestParam("phone") String phone, HttpSession session){
        userService.code(phone, session);
        return Result.success("成功");
    }
    @PostMapping("/login")
    public Result<UserDto> loginByCode(@RequestBody LoginDto loginDto, HttpSession session){
        return userService.loginByCode(loginDto,session);
    };
    @GetMapping("/login")
    public Result<UserDto> login(String username,String password){

        return userService.loginByUsername(username,password);
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){

        userService.logout(request);
        return Result.success("注销成功");
    }
}
