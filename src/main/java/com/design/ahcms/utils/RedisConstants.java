package com.design.ahcms.utils;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY="login:code:";
    public static final Long LOGIN_CODE_TTL= 3L;
    public static final String LOGIN_TOKEN="login:token:";
    public static final Long LOGIN_USER_TTL=30L;
    public static final Long CACHE_NULL_TTL=30L;
    public static final String CACHE_ARTICLE_KEY="cache:article:";
    public static final Long CACHE_ARTICLE_TTL=30L;
}
