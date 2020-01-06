package com.wotrd.elasticsearch.service;


import com.wotrd.elasticsearch.pojo.entity.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 添加索引
     */
    public void add(){
        BookDO bookDO = new BookDO();
        elasticsearchTemplate.putMapping(BookDO.class, bookDO);
    }

}
