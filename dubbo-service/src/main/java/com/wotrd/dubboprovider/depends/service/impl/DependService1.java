package com.wotrd.dubboprovider.depends.service.impl;

import com.wotrd.dubboprovider.depends.service.IDependService;
import org.springframework.stereotype.Service;

@Service("001DependService")
public class DependService1 implements IDependService {
    @Override
    public void depend() {
        System.out.println("dependService1");
    }
}
