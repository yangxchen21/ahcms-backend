package com.design.ahcms.mapper;

import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void saveUserPassword(User user);
    void saveUserDetail(UserDetail userDetail);
    User getUser(String name);
    void deleteUser(Long id);
}
