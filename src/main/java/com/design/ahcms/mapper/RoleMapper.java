package com.design.ahcms.mapper;

import com.design.ahcms.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> getRoleList(Long id);
}
