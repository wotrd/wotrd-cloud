package com.wotrd.feign.designmode.chain;

/**
 * @ClassName: ValidateChain
 * @Description: 校验点接口
 * @Author: wotrd
 * @Date: 2020/10/29 19:37
 */
public interface ValidateSlot<T> {

    /**
     * 校验参数
     */
    void validate(T t);

}