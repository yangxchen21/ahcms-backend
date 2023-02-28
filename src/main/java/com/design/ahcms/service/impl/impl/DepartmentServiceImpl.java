package com.design.ahcms.service.impl.impl;

import com.design.ahcms.domain.Department;
import com.design.ahcms.domain.School;
import com.design.ahcms.mapper.DepartmentMapper;
import com.design.ahcms.service.impl.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getListById(Long id) {
        return departmentMapper.queryBySchoolId(id);
    }
}
