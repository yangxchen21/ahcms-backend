package com.design.ahcms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Article;
import com.design.ahcms.service.impl.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/page")
    public Result<Page<Article>> page(int pageIndex,int pageSize,String title){
        Page<Article> page=new Page<>(pageIndex,pageSize);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(title!=null,Article::getTitle,title);
        return Result.success(articleService.page(page,lambdaQueryWrapper) );
    }
}
