package com.design.ahcms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMSPermission {
    private  long id;
    private String name;
    private String url;
    private String method;
}
