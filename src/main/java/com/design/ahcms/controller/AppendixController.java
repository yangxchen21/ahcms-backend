package com.design.ahcms.controller;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Appendix;
import com.design.ahcms.domain.Img;
import com.design.ahcms.service.impl.AppendixService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@PropertySource(value = "classpath:application.yml")
@RestController

@RequestMapping("/appendix")
public class AppendixController {
    @Resource
    private AppendixService appendixService;
    @Value("${ahcmstore.appendixPath}")
    private String url;
    @PostMapping("/upload")
    public Result<List<Long>> upload(@RequestBody  MultipartFile[] files){
        return Result.success(  appendixService.upload(appendixService.fileToAppendix(files)));
    }
    @GetMapping("/list")
    public Result<Page<Appendix>> list(int pageIndex,int pageSize,String name){
        return Result.success(appendixService.pageList(pageIndex,pageSize,name));
    }
    @GetMapping
    public ResponseEntity<InputStreamResource> download(Long id) throws IOException {
        Appendix appendix=appendixService.getById(id);
        String fileName=appendix.getName();
        String path=appendix.getUrl();
        FileSystemResource file=new FileSystemResource(path);
        HttpHeaders headers=new HttpHeaders();
//        headers.add("Content-Disposition","attachment; filename=\""+fileName+"\"");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
