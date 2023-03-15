package com.design.ahcms.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.design.ahcms.domain.User;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterDto extends User {
    private String name;
    private String email;
    private String idcardNumber;
    private String address;
    
    private Long departmentId;
    private Integer type;
}
