package com.design.ahcms.service.impl;

import com.design.ahcms.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    List<Role> getRoleByUserId(long id);
}
