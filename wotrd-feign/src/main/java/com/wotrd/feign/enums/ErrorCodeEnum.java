package com.wotrd.feign.enums;


public enum ErrorCodeEnum {

    SUCCESS(200, "success"),
    FAILED(500, "failed");

    private Integer code;

    private String info;

    ErrorCodeEnum(Integer code, String info){
        this.code =code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
