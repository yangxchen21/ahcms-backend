package com.design.ahcms.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {
    private Long id;
    private String name;
    private Long schoolId;
}
