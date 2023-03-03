package com.design.ahcms.dto;

import com.design.ahcms.domain.User;

import java.util.List;

public class LoginDto extends User {
    private List<String> roles;
}
