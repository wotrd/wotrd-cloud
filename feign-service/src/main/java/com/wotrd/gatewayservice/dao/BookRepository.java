package com.wotrd.gatewayservice.dao;

import com.wotrd.gatewayservice.model.entity.BookDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ElasticsearchRepository<BookDO, Long> {

//    List<BookDO> findBookDOByAuthorNameAndAndPriceBetween(String name, Integer price);

}
