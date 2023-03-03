package com.design.ahcms.mapper;

import com.design.ahcms.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentMapper {
    List<Department> queryBySchoolId(Long id);
}
