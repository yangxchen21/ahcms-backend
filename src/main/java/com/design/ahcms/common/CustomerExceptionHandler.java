package com.design.ahcms.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public String ErrorHandler(AuthenticationException e){
        log.error("权限认证失败",e);
        return "没有通过权限认证";
    }
    @ExceptionHandler(Exception.class)
    public Result Execption(Exception e) {
        e.printStackTrace();
        log.error("未知异常！", e);
        return Result.error(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMsg());
    }
}
