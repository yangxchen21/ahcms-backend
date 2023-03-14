package com.design.ahcms.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Img;
import com.design.ahcms.service.impl.ImgService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/img")

public class ImgController {
    @Resource
    private ImgService imgService;

    @PostMapping("/upload")
    public Result<Img> upload(@RequestBody MultipartFile file) {

        return Result.success(imgService.uploadImg(file));
    }
    @PostMapping("/delete")
    public Result<String> delete(String[] urls){
        return null;
    }
}
