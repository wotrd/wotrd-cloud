package com.wotrd.feign.service;


import com.wotrd.feign.mapper.BookMapper;
import com.wotrd.feign.model.entity.Books;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * elasticsearch中本没有修改，它是先删除再新增
 * elasticsearch本质也是存储数据，它不支持事物
 */
@Service
@Slf4j
public class BookService {

    /**
     * 用于创建和删除索引
     */
//    @Autowired
//    private ElasticsearchOperations operations;

    /**
     * 创建索引
     */
    public void createIndex(String indexName) {
        // 创建索引，会根据BookDO类的@Document注解信息来创建
//        operations.createIndex(BookDO.class);
        // 配置映射，会根据BookDO类中的id、Field等字段来自动完成映射
//        operations.putMapping(BookDO.class);
        //创建指定名字的索引
//        operations.createIndex(indexName);
//        operations.putMapping(OrderDO.class);
    }

    /**
     * 删除索引
     */
    public void delete(String indexName) {
//        operations.deleteIndex(BookDO.class);
        // 根据索引名字删除
//        operations.deleteIndex(indexName);
    }

    /**
     * ---------------------------- 使用Repository查询，类似于jpa，进行索引的增删改查 ---------------------------------------
     */
//    @Autowired
//    private BookRepository bookRepository;

    /**
     * 保存图书
     *
     * @param bookDO
     * @return
     */
//    public BookDO save(BookDO bookDO) {
//        BookDO result = bookRepository.save(bookDO);
//        return result;
//    }

    /**
     * 根据ID删除
     *
     * @param id
     */
//    public void deleteById(Long id) {
//        bookRepository.deleteById(id);
//    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
//    public Optional<BookDO> findById(Long id) {
//        return bookRepository.findById(id);
//    }

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
//    public Iterable<BookDO> findAllById(List<Long> ids) {
//        return bookRepository.findAllById(ids);
//    }

    /**
     * 查询全部，分页，排序
     */
//    public void findAll() {
//        //查询全部
//        Iterable<BookDO> all = bookRepository.findAll();
//        log.info("all:"+JSONObject.toJSONString(all.iterator()));
//        //排序查询全部
//        Iterable<BookDO> sortAll = bookRepository.findAll(Sort.by("price").descending().and(Sort.by("id").ascending()));
//        log.info("sortAll:"+JSONObject.toJSONString(sortAll.iterator()));
//        //根据列表ID集合查询
//        Long[] ids = new Long[1];
//        ids[0] = 1L;
//        Iterable<Long> iterator = new ArrayIterator(ids);
//        Iterable<BookDO> allById = bookRepository.findAllById(iterator);
//        log.info("allById:"+JSONObject.toJSONString(allById.iterator()));
//        //分页
//        Page<BookDO> bookDOS = bookRepository.findAll(PageRequest.of(1, 2));
//        log.info("bookDOS:"+JSONObject.toJSONString(bookDOS.getContent()));
//    }


    /**
     * ---------------------------- Repository使用QueryBuilder查询 ---------------------------------------
     */


//    public void queryBuilderSearch(String name) {
//        // 构建查询条件
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        // 添加基本分词查询
////        queryBuilder.withQuery(QueryBuilders.matchQuery("name", name));
////        queryBuilder.withQuery(QueryBuilders.termQuery("name", name));
//        //模糊
//        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("name", name));
//        //范围
//        queryBuilder.withQuery(QueryBuilders.rangeQuery("price").from(3000).to(4000));
//        //逻辑与关系
//        queryBuilder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", name))
//                .must(QueryBuilders.termQuery("authorName", name))
//        );
//        // 搜索，获取结果
//        Page<BookDO> items = this.bookRepository.search(queryBuilder.build());
//        // 总条数
//        long total = items.getTotalElements();
//        log.info("total = " + total);
//        for (BookDO bookDO : items) {
//            log.info(bookDO.toString());
//        }
//    }

    @Autowired
    private BookMapper bookMapper;

    public List<Books> list() {
        return bookMapper.selectAll();
    }
}
