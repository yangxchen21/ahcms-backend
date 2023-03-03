package com.design.ahcms.mapper;

import com.design.ahcms.domain.CMSPermission;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface PermissionMapper {
    List<CMSPermission> getPermissionList(List<Long> ids);
}
