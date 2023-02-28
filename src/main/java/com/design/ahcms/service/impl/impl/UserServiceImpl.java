package com.design.ahcms.service.impl.impl;

import com.design.ahcms.domain.User;
import com.design.ahcms.mapper.UserMapper;
import com.design.ahcms.service.impl.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public long saveUser(User user) {
        userMapper.saveUser(user);
        return user.getId();
    }
}
