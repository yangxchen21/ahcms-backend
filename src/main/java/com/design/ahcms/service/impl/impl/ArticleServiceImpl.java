package com.design.ahcms.service.impl.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.design.ahcms.common.Result;
import com.design.ahcms.domain.Appendix;
import com.design.ahcms.domain.Article;
import com.design.ahcms.domain.ArticleAppendix;
import com.design.ahcms.dto.ArticleDto;
import com.design.ahcms.service.impl.AppendixService;
import com.design.ahcms.service.impl.ArticleAppendixService;
import com.design.ahcms.service.impl.ArticleService;
import com.design.ahcms.mapper.ArticleMapper;
import com.design.ahcms.utils.RedisCacheClient;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.design.ahcms.utils.RedisConstants.*;

/**
* @author 24088
* @description 针对表【article】的数据库操作Service实现
* @createDate 2023-03-03 15:43:30
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisCacheClient redisCacheClient;
    @Resource
    private AppendixService appendixService;
    @Resource
    private ArticleAppendixService articleAppendixService;
    @Override
    public Result<Article> queryById(Long id){
        Article article=redisCacheClient.queryWithPassThrough(CACHE_ARTICLE_KEY,id, Article.class,this::getById,CACHE_ARTICLE_TTL,TimeUnit.MINUTES);
        if(article==null)   return Result.error(404,"未找到");
        return Result.success(article);
    }

    @Override
    public Long saveOne(Article article) {
        this.save(article);
        return article.getId();
    }

    @Override
    public void saveWithAppendix(ArticleDto articleDto) {
        //1.保存article到表
        Article article=(Article) articleDto;

        //2.判断有无附件，有则保存到表，并保存到关系表
        List<Long> appendixIdList=articleDto.getAppendixIds();
        this.save(article);
        Long articleId=article.getId();
        if(appendixIdList.size()>0){

            //在将关系保存到关系表
            for(Long id:appendixIdList){
                ArticleAppendix articleAppendix=new ArticleAppendix();
                articleAppendix.setAppendixId(id);
                articleAppendix.setArticleId(articleId);
                articleAppendixService.save(articleAppendix);
            }
        }
    }

    @Override
    public void removeWithAppendix(Long id) {

        LambdaQueryWrapper<ArticleAppendix> eqArticleId = new LambdaQueryWrapper<>();
        eqArticleId.eq(ArticleAppendix::getArticleId, id);
        List<ArticleAppendix> articleAppendixList = articleAppendixService.list(eqArticleId);
        for(ArticleAppendix articleAppendix:articleAppendixList){
            Long appendixId=articleAppendix.getAppendixId();
            appendixService.removeById(appendixId);
        }
        articleAppendixService.remove(eqArticleId);
        this.removeById(id);
    }

    //    public Result<Article> queryById(Long id) {
//        //从redis查询缓存
//        String key=CACHE_ARTICLE_KEY+id;
//        String articleJson = stringRedisTemplate.opsForValue().get(key);
//        //判断是否存在
//        if (StrUtil.isNotBlank(articleJson)) {
//            //存在直接返回
//            Article article = JSONUtil.toBean(articleJson, Article.class);
//           return Result.success(article);
//        }
//        //判断命中是否为空值
//        if(articleJson!=null){
//            return Result.error(404,"不存在");
//        }
//        //不存在查询数据库
//        Article article = this.getById(id);
//        if(article==null){
//            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
//            return Result.error(404,"不存在");
//        }
//        String articleStr = JSONUtil.toJsonStr(article);
//        stringRedisTemplate.opsForValue().set("cache:article:"+id,articleStr);
//        //数据库中不存在返回错误
//        //存在写入redis并返回
//        return Result.success(article);
//    }
    private boolean tryLock(String key){
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }
    private void unlock(String key){
        stringRedisTemplate.delete(key);
    }
}




