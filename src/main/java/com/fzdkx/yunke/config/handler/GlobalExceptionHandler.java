package com.fzdkx.yunke.config.handler;

import com.fzdkx.yunke.common.CodeEnum;
import com.fzdkx.yunke.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 发着呆看星
 * @create 2024/6/15
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @Order(100)
    public Result<String> handlerRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail(CodeEnum.SERVER_ERROR);
    }

    @Order(10)
    @ExceptionHandler(DataAccessException.class)
    public Result<String> handlerDataAccessException(DataAccessException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail(CodeEnum.DATA_ACCESS_ERROR);
    }

    @Order(1)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<String> handlerDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail(CodeEnum.DATA_INTEGRITY_VIOLATION);
    }

}
