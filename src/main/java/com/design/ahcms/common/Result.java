package com.design.ahcms.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class Result<T>implements Serializable {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message) {
        this.code=code;
        this.message=message;
    }

    public Result() {}

    public static <T> Result<T> success(T data){
        Result<T> result=new Result<T>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }
    public static <T> Result<T> error(int code,String message){
        return new Result<>(code,message);
    }
}
