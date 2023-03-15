package com.design.ahcms.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    private Long id;
    private String name;
}
