package com.design.ahcms.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @TableName article
 */
@Data

public class Article implements Serializable {
    
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    private Long views;

    private String publisher;

    private String content;

    private Integer type;

    private String title;

    private static final long serialVersionUID = 1L;


}
