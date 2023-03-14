package com.design.ahcms.controller;

import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Department;
import com.design.ahcms.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 根据学校id查部门
     * @param id 学校id
     * @return 部门列表
     */

    @GetMapping("/{id}")
    public Result<List<Department>> listBySchoolId(@PathVariable Long id){
        List<Department> departments=departmentService.getListById(id);
        return Result.success(departments);
    }
}
