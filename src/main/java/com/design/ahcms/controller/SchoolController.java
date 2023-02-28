package com.design.ahcms.controller;

import com.design.ahcms.common.Result;
import com.design.ahcms.domain.School;
import com.design.ahcms.service.impl.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 查询学校列表
     * @return 学校
     */
    @GetMapping
    public Result<List<School>> list(){
        List<School> schools = schoolService.list();
        return Result.success(schools);
    }
}
