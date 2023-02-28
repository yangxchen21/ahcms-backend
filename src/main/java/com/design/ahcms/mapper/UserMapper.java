package com.design.ahcms.mapper;

import com.design.ahcms.domain.User;

import java.util.List;
public interface UserMapper {
    List<User> selectUserList();
    void saveUser(User user);
    void saveUserAndPassword(User user,String password);
}
