package com.design.ahcms.dto;

import com.design.ahcms.domain.Article;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ArticleDto extends Article {
    private List<Long> appendixIds;
}
