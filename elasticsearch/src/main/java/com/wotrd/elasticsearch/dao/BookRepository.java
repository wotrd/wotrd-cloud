package com.wotrd.elasticsearch.dao;

import com.wotrd.elasticsearch.model.entity.BookDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ElasticsearchRepository<BookDO, Long> {

//    List<BookDO> findBookDOByAuthorNameAndAndPriceBetween(String name, Integer price);

}
