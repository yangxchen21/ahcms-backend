package com.design.ahcms.controller;

import com.design.ahcms.common.Result;
import com.design.ahcms.domain.ArticleImg;
import com.design.ahcms.dto.ArticleImgSaveDto;
import com.design.ahcms.service.impl.ArticleImgService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/articleimg")
public class ArticleImgController {
    @Resource
    private ArticleImgService articleImgService;
    @PostMapping
  public Result<String> save(@RequestBody ArticleImgSaveDto articleImgSaveDto){
        return  articleImgService.save(articleImgSaveDto)?Result.success("保存成功"):Result.error(400,"保存错误");
    }
}
