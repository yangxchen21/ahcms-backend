package com.design.ahcms.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import com.design.ahcms.dto.LoginDto;
import com.design.ahcms.dto.RegisterDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;


public interface UserService extends IService<User> {
    @Transactional
    void register(RegisterDto registerDto);
    
    User getUserByName(String name);

    void code(String phone, HttpSession session);
    Result<String> loginByCode(LoginDto loginDto,HttpSession session);
}
