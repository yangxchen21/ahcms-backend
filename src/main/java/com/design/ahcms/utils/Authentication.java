package com.design.ahcms.utils;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.design.ahcms.domain.CMSPermission;
import com.design.ahcms.domain.Role;
import com.design.ahcms.mapper.PermissionMapper;
import com.design.ahcms.mapper.RoleMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Authentication implements StpInterface {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        List<Role> roleList = roleMapper.getRoleList(Long.parseLong((String)loginId));
        List<Long> roleIds=roleList.stream().map(Role::getId).toList();

        //查询用户权限列表
        List<CMSPermission> list = permissionMapper.getPermissionList(roleIds);
        List<String> permission=list.stream().map(item->
             item.getUrl()+"|"+item.getMethod()
        ).toList();

        return permission;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        //查询用户对应角色列表
        List<Role> roleList = roleMapper.getRoleList(Long.parseLong((String)loginId));
        List<String> roleNames=roleList.stream().map(Role::getName).collect(Collectors.toList());
        return roleNames;
    }
}
