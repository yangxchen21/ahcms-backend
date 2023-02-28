package com.design.ahcms.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private Long id;
    private String accountNumber;
    private String name;
    private String phone;
    private String sex;
    private String idcardNumber;
    private int type;
    private String email;
    private String address;
    private String postCode;
    private int status;
    private Long departmentId;
}
