package com.wotrd.client.service;


import com.wotrd.client.mapper.ContentMapper;
import com.wotrd.client.model.entity.ContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ContentService
 * @Description: 内容服务
 * @Author: wotrd
 * @Date: 2020/09/01 23:20
 */
@Service
public class ContentService {


    @Autowired
    private ContentMapper contentMapper;

    public List<ContentDO> queryAllContent(Long categoryId) {
        return contentMapper.queryByCategoryid(categoryId);
    }
}
