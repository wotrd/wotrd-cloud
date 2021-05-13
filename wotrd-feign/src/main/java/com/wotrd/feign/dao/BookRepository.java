package com.wotrd.feign.dao;

import com.wotrd.feign.model.entity.BookDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends ElasticsearchRepository<BookDO, Long> {

    List<BookDO> findBookDOByAuthorNameAndAndPriceBetween(String name, Integer price);

}
