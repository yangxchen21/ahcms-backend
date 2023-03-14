package com.design.ahcms.service.impl;

import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.design.ahcms.dto.ArticleDto;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 24088
* @description 针对表【article】的数据库操作Service
* @createDate 2023-03-03 15:43:30
*/
public interface ArticleService extends IService<Article> {
    Result<Article> queryById(Long id);
    Long saveOne(Article article);
    @Transactional
    void saveWithAppendix(ArticleDto articleDto);

    /**
     * 删除文章与附件
     * @param id 文章id
     */
    @Transactional
    void removeWithAppendix(Long id);
}
