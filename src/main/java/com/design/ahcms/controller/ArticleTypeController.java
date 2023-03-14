package com.design.ahcms.controller;

import com.design.ahcms.common.Result;
import com.design.ahcms.domain.ArticleType;
import com.design.ahcms.service.impl.ArticleTypeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController

@RequestMapping("/articletype")
public class ArticleTypeController {
    @Resource
    private ArticleTypeService articleTypeService;

    /**
     * 查询一级种类
     * @return 列表
     */
    @GetMapping("/listv1")
    public Result<List<ArticleType>> lv1List(){
        return Result.success(articleTypeService.getLv1List());
    }
    @GetMapping("/listv2")
    public Result<List<ArticleType>> lv2ListById(@RequestParam Long id){
        return Result.success(articleTypeService.getLv2ListByLv1Id(id));
    }
}
