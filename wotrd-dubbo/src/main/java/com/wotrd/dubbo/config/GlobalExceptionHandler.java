package com.wotrd.dubbo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String errorHandler(MethodArgumentNotValidException e) {

        String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("参数错误:"+errorMsg);

        return errorMsg;
    }

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }
}
