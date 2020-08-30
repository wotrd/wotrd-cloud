package com.wotrd.dubbo.depends.service.impl;

import com.wotrd.dubbo.depends.service.IDependService;
import org.springframework.stereotype.Service;

@Service("002DependService")
public class DependService2 implements IDependService {
    @Override
    public void depend() {
        System.out.println("DependService2");
    }
}
