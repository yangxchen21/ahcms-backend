package com.design.ahcms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
   private String username;
   private String phone;
   private String password;
   private String salt;
   private Long detailId;
   private int status;
}
