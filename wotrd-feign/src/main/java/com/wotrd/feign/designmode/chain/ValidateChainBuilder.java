package com.wotrd.feign.designmode.chain;

import com.wotrd.feign.designmode.chain.slot.NameValidateSlot;
import com.wotrd.feign.designmode.chain.slot.SexValidateSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @ClassName: ValidateChainBuild
 * @Description: 责任链建造器
 * @Author: wotrd
 * @Date: 2020/10/29 19:38
 */
@Component
public class ValidateChainBuilder<T> {

    @Autowired
    private NameValidateSlot nameValidateSlot;

    @Autowired
    private SexValidateSlot sexValidateSlot;

    /**
     * 构建责任链对象
     *
     * @return
     */
    public ValidateChain build() {
        ValidateChain chain = new ValidateChain();
        chain.addLast(sexValidateSlot);
        chain.addLast(nameValidateSlot);
        return chain;
    }

}
