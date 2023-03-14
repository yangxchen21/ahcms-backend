package com.design.ahcms.controller;

import cn.hutool.core.lang.UUID;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Appendix;
import com.design.ahcms.domain.Img;
import com.design.ahcms.service.impl.AppendixService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j

@RestController

@RequestMapping("/appendix")
public class AppendixController {
    @Resource
    private AppendixService appendixService;

    @PostMapping("/upload")
    public Result<List<Long>> upload(@RequestBody  MultipartFile[] files){
        return Result.success(  appendixService.upload(appendixService.fileToAppendix(files)));
    }
}
