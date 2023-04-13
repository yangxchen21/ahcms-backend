package com.design.ahcms.dto;

import lombok.Data;

import java.util.List;
@Data
public class ArticleImgSaveDto {
    private Long articleId;
    private List<Long> imgIds;
}
