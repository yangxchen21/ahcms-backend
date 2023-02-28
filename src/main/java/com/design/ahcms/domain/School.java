package com.design.ahcms.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class School implements Serializable {
    private Long id;
    private String name;
    private String address;
    private Integer level;
}
