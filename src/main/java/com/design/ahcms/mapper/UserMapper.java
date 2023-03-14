package com.design.ahcms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    void saveUserPassword(User user);
    void saveUserDetail(UserDetail userDetail);
    User getUser(String name);
    void deleteUser(Long id);
}
