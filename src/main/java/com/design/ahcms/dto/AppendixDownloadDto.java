package com.design.ahcms.dto;

import lombok.Data;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
@Data
public class AppendixDownloadDto {
  private InputStreamResource data;
  private String fileName;
}
