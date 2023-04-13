package com.design.ahcms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Article;
import com.design.ahcms.dto.ArticleDto;
import com.design.ahcms.dto.ArticleSearchDto;
import com.design.ahcms.dto.ArticleWithImgDto;
import com.design.ahcms.service.impl.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController

@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
//    @GetMapping("/page")
//    public Result<Page<Article>> page(int pageIndex, int pageSize, String title,Integer type){
//        Page<Article> page=new Page<>(pageIndex,pageSize);
//        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.like(title!=null,Article::getTitle,title);
//        lambdaQueryWrapper.eq(type!=null,Article::getType,type);
//        return Result.success(articleService.page(page,lambdaQueryWrapper) );
//    }
    @GetMapping("/{id}")
    public Result<Article> getById(@PathVariable  Long id){
        return articleService.queryById(id);
    }

    /**
     * 查询基本信息列表
     * @param pageIndex
     * @param pageSize
     * @param title
     * @param type
     * @return
     */
    @GetMapping("/basic-page")
    public Result<Page<Article>> pageQueryBasicInfo(int pageIndex,int pageSize,String title,Integer type,String publisher){
        Page<Article> page=new Page<>(pageIndex,pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(title!=null,Article::getTitle,title);
        queryWrapper.lambda().eq(type!=null,Article::getType,type);
        queryWrapper.lambda().like(publisher!=null,Article::getPublisher,publisher);
        queryWrapper.select("title","publisher","create_time","update_time","id","type");
        return Result.success(articleService.page(page,queryWrapper) );
    }

    /**
     * 批量删除文章
     * @param ids 文章id
     * @return string
     */
    @PostMapping("/delete")
    public Result<String> deleteByIds(@RequestBody List<Long> ids){
       for(Long id:ids){
           articleService.removeWithAppendix(id);
       }
    return Result.success("删除成功");

    }
    @PostMapping
    public Result<Long> saveArticleWithAppendix(@RequestBody ArticleDto articleDto){
        articleService.saveWithAppendix(articleDto);

        return Result.success(articleDto.getId());
    }
    @GetMapping("/page")
    public Result<Page<Article>>pageWithImgTest(int pageIndex, int pageSize, String title,Integer type){

        return Result.success( articleService.pageWithImg(pageIndex,pageSize,title,type));
    }
    @GetMapping
    public Result<List<ArticleSearchDto>> queryByTitle(@RequestParam String title){

        return Result.success(articleService.queryByTitle(title));
    }
}
