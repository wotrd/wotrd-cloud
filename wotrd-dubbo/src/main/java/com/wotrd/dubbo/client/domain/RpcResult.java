package com.wotrd.dubbo.client.domain;


import lombok.Data;
import org.apache.dubbo.apidocs.annotations.ResponseProperty;

/**
 * @className RpcResult
 * @description TODO
 * @Author wotrd
 * @date 2021/1/22 17:37
 */
@Data
public class RpcResult<T> {

    @ResponseProperty(value = "Response code", example = "500")
    private Integer code;
    @ResponseProperty(value = "result msg", example = "success")
    private String msg;

    /**
     * 返回体
     */
    private T entity;

    public static RpcResult buildSuccess(Object entity) {
        RpcResult result = new RpcResult();
        result.setEntity(entity);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static RpcResult buildError(String msg) {
        RpcResult result = new RpcResult();
        result.setEntity(null);
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static RpcResult buildError(String msg, Object entity) {
        RpcResult result = new RpcResult();
        result.setEntity(entity);
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static RpcResult buildError(Integer code, String msg, Object entity) {
        RpcResult result = new RpcResult();
        result.setEntity(entity);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
