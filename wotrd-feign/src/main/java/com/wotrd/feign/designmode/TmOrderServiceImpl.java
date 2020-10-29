package com.wotrd.feign.designmode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName: JdOrderServiceImpl
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/29 17:44
 */
@Slf4j
@Service("tmOrderServiceImpl")
public class TmOrderServiceImpl implements OrderService {

    @Override
    public void makeOrder() {
        log.info("tianmao make order");
    }

}
