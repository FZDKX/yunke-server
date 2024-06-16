package com.fzdkx.yunke.common;

import lombok.Data;

/**
 * @author 发着呆看星
 * @create 2024/6/7
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> success(){
        return new Result<>(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> fail(){
        return new Result<>(CodeEnum.FAIL.getCode(),CodeEnum.FAIL.getMessage());
    }

    public static <T> Result<T> success(T data){
        Result<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(T data){
        Result<T> result = fail();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(CodeEnum codeEnum){
        return new Result<>(codeEnum.getCode(),codeEnum.getMessage());
    }
}
