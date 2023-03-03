package com.design.ahcms.domain;

import lombok.Data;

@Data
public class UserDetail {
    private Long id;
    private String name;
    private String email;
    private String idcardNumber;
    private String address;
    private Long departmentId;
    private Integer type;
}
