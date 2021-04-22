package com.wotrd.dubbo.common.designmode;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/18 21:02
 */
@Slf4j
public class Try<T, R> {

    /**
     * 不带返回值
     *
     * @param t
     * @param consumer
     */
    public void executeWithTry(T t, Consumer<T> consumer) {
        try {
            consumer.accept(t);
        } catch (Exception e) {
            log.error("executeWithTry error {}", t);
        }
    }

    /**
     * 带返回值
     *
     * @param t
     * @param function
     * @param r
     * @return
     */
    public R executeWithTry(T t, Function<T, R> function, R r) {
        try {
            function.apply(t);
        } catch (Exception e) {
            log.error("executeWithTry error {}", t);
        }
        return r;
    }


}
