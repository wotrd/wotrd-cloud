package com.wotrd.feign.designmode.chain.slot;

import com.wotrd.feign.designmode.chain.ValidateSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ValidateChain
 * @Description: 性别校验
 * @Author: wotrd
 * @Date: 2020/10/29 19:37
 */
@Slf4j
@Component
public class SexValidateSlot<T> implements ValidateSlot<T> {

    /**
     * 校验性别参数
     */
    public void validate(T t) {
        log.info("SexValidateSlot-----");
    }


}