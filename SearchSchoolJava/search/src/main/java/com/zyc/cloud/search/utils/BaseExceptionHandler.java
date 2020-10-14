package com.zyc.cloud.search.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZengYc
 * @Date 2020/10/13 0:05
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultUtil err(Exception e){
        e.printStackTrace();
        return new ResultUtil(false, StatusCode.ERROR,e.getMessage());
    }
}
