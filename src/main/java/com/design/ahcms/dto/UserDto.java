package com.design.ahcms.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserDto {
    private String phone;
    private String username;
    
    private Long id;
    private Integer status;
    private String  token;
}
