package com.wotrd.feign.designmode.chain.slot;

import com.wotrd.feign.designmode.chain.ValidateSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ValidateChain
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/29 19:37
 */
@Slf4j
@Component
public class NameValidateSlot<T> implements ValidateSlot<T> {

    /**
     * 校验名字参数
     */
    public void validate(T t) {
        log.info("NameValidateSlot-----");
    }


}