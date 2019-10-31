package com.example.service;

import com.example.first.domain.Order;
import com.example.first.mapper.FirstMapper;
import com.example.second.mapper.SecondMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wotrd
 */
@Service
public class DsService {

    @Autowired
    private FirstMapper firstMapper;

    @Autowired
    private SecondMapper secondMapper;


    public void get(Long id) {
        Order order = firstMapper.getOrder(id);
        com.example.second.domain.Order order1 = secondMapper.getOrder(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Long count, Long id){

        firstMapper.update(13L, 4L);
        firstMapper.update(16L, 2L);

        secondMapper.update(14L, 4L);


    }


}
