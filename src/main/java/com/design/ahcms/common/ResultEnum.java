package com.design.ahcms.common;

import lombok.Data;


public enum ResultEnum {
    SUCCESS(200,"成功"),
    FAIL(400,"失败"),
    UNAUTHORIZED(401,"未授权"),
    NOT_FOUND(404,"资源丢失"),
    INTERNAL_SERVER_ERROR(500,"服务器出问题了！")
    ;

    private final int code;
    private final String msg;

    ResultEnum(int code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public int getCode(){
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
