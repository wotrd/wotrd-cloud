package com.wotrd.feign.designmode.chain;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: ValidateChain
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/29 19:50
 */
public class ValidateChain<T> {

    /**
     * 校验插槽点列表
     */
    List<ValidateSlot> list = new LinkedList<>();

    /**
     * 校验条件
     *
     * @param t
     */
    public void validate(T t) {
        list.forEach(slot -> {
            slot.validate(t);
        });
    }

    /**
     * 添加校验点
     *
     * @param next
     */
    public void addLast(ValidateSlot next) {
        list.add(next);

    }

}
