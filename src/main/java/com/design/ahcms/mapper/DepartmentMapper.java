package com.design.ahcms.mapper;

import com.design.ahcms.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> queryBySchoolId(Long id);
}
