package com.design.ahcms.service.impl.impl;

import com.design.ahcms.domain.User;
import com.design.ahcms.domain.UserDetail;
import com.design.ahcms.dto.RegisterDto;
import com.design.ahcms.mapper.UserMapper;
import com.design.ahcms.service.impl.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional
    public void register(RegisterDto registerDto) {
        UserDetail userDetail = new UserDetail();
        userDetail.setName(registerDto.getName());
        userDetail.setEmail(registerDto.getEmail());
        userDetail.setIdcardNumber(registerDto.getIdcardNumber());
        userDetail.setAddress(registerDto.getAddress());
        userDetail.setDepartmentId(registerDto.getDepartmentId());
        userDetail.setType(registerDto.getType());
        userMapper.saveUserDetail(userDetail);
        ((User) registerDto).setDetailId(userDetail.getId());
        userMapper.saveUserPassword((User) registerDto);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUser(name);
    }
}
