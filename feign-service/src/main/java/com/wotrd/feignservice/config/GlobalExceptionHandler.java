package com.wotrd.feignservice.config;

import com.wotrd.nacos.common.conf.GlobalResponse;
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
    public GlobalResponse errorHandler(MethodArgumentNotValidException e) {

        String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("参数错误:"+errorMsg);

        return GlobalResponse.builder().code("100").msg(errorMsg).build();
    }

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public GlobalResponse errorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return GlobalResponse.builder().code("100").msg(e.getMessage()).build();
    }
}
