package com.design.ahcms.service.impl.impl;

import com.design.ahcms.domain.Role;
import com.design.ahcms.mapper.RoleMapper;
import com.design.ahcms.mapper.UserMapper;
import com.design.ahcms.service.impl.RoleService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleByUserId(long id) {
       return  roleMapper.getRoleList(id);
    }
}
