package com.zyc.cloud.search.utils;

import lombok.Data;

/**
 * @author ZengYc
 * @Date 2020/10/14 0:07
 */
@Data
public class ResultUtil<T> {
    //是否成功
    private boolean flag;
    //返回码
    private Integer code;
    //返回消息
    private String message;
    //返回数据
    private T data;

    public ResultUtil(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public ResultUtil(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public ResultUtil() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "操作成功!";
    }

    public static ResultUtil success(){
        return new ResultUtil();
    }

    public static ResultUtil success(String message,Object data){
        return new ResultUtil(true,StatusCode.OK, message,data);
    }

    public static ResultUtil fail(String message){
        return new ResultUtil(false, StatusCode.ERROR,message);
    }

}
