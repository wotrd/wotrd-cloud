package com.example.service.impl;

import com.example.first.domain.Order;
import com.example.first.mapper.FirstMapper;
import com.example.second.mapper.SecondMapper;
import com.example.service.DsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wotrd
 */
@Service
public class DsServiceImpl implements DsService {

    @Autowired
    private FirstMapper firstMapper;

    @Autowired
    private SecondMapper secondMapper;


    @Override
    public void get(Long id) {
        Order order = firstMapper.getOrder(id);
        com.example.second.domain.Order order1 = secondMapper.getOrder(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long count, Long id){

        firstMapper.update(13L, 4L);
        firstMapper.update(15L, 2L);

        secondMapper.update(18L, 4L);
        if (count>10){
            throw new RuntimeException();
        }


    }


}
