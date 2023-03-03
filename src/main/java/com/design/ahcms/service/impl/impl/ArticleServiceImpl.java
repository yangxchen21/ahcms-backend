package com.design.ahcms.service.impl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.design.ahcms.domain.Article;
import com.design.ahcms.service.impl.ArticleService;
import com.design.ahcms.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author 24088
* @description 针对表【article】的数据库操作Service实现
* @createDate 2023-03-03 15:43:30
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




