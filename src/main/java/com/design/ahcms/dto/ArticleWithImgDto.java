package com.design.ahcms.dto;

import com.design.ahcms.domain.Article;
import lombok.Data;

@Data
public class ArticleWithImgDto extends Article {
    private String imgUrl;
}
