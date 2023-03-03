package com.design.ahcms.service.impl;

import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import com.design.ahcms.dto.RegisterDto;


public interface UserService {
    void register(RegisterDto registerDto);
    User getUserByName(String name);
}
