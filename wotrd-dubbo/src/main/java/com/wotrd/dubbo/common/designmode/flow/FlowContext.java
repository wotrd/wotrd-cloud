package com.wotrd.dubbo.common.designmode.flow;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:14
 */
@Data
@Slf4j
public class FlowContext<REQ, RES, CTX> {

    private CTX ctx;

    private REQ request;

    private RES result;

    public void init(Class<CTX> cls){
        try {
            this.ctx = cls.newInstance();
        } catch (Exception e) {
            log.error("flow context init error, cls:{}", cls);
        }
    }
}
