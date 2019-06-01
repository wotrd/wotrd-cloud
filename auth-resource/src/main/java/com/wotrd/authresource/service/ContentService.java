package com.wotrd.authresource.service;


import com.wotrd.authresource.domain.TbContent;
import com.wotrd.authresource.mapper.TbContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {


    @Autowired
    private TbContentMapper contentMapper;

    public List<TbContent> queryAllContent(Long categoryId) {
        return contentMapper.queryByCategoryid(categoryId);
    }
}
