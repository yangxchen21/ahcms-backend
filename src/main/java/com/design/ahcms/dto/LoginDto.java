package com.design.ahcms.dto;

import com.design.ahcms.domain.User;
import lombok.Data;

import java.util.List;
@Data
public class LoginDto {
    private String phone;
    private String username;
    private String password;
    private String code;
}
