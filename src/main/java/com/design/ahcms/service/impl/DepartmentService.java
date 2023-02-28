package com.design.ahcms.service.impl;

import com.design.ahcms.domain.Department;
import com.design.ahcms.domain.School;

import java.util.List;

public interface DepartmentService {
    List<Department> getListById(Long id);
}
