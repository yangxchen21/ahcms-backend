package com.design.ahcms.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {
    
    private Long id;
    private String name;
    
    private Long schoolId;
}
