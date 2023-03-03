package com.design.ahcms.dto;

import com.design.ahcms.domain.User;
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
