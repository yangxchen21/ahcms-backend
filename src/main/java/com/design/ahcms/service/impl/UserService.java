package com.design.ahcms.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import com.design.ahcms.dto.LoginDto;
import com.design.ahcms.dto.RegisterDto;
import com.design.ahcms.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;


public interface UserService extends IService<User> {
    @Transactional
    void register(RegisterDto registerDto);
    


    void code(String phone, HttpSession session);
    Result<UserDto> loginByCode(LoginDto loginDto, HttpSession session);
    Result<UserDto> loginByUsername(String username,String password);
    void logout(HttpServletRequest request);
}
