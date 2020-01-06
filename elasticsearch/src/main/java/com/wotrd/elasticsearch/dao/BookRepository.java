package com.wotrd.elasticsearch.dao;

import com.wotrd.elasticsearch.pojo.entity.BookDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ElasticsearchRepository<BookDO, Long> {
}
