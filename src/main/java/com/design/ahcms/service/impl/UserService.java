package com.design.ahcms.service.impl;

import com.design.ahcms.domain.User;

import java.util.List;

public interface UserService {
    public List<User> selectUserList();
    public long saveUser(User user);
}
