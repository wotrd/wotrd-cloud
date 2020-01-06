package com.wotrd.elasticsearch.controller;

import com.wotrd.elasticsearch.dao.BookRepository;
import com.wotrd.elasticsearch.pojo.entity.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RequestMapping("es")
@RestController
public class ElasticSearchController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("add")
    public BookDO get(@RequestBody BookDO bookDO){
        return bookRepository.save(bookDO);
    }

    @RequestMapping("delete")
    public String delete(Long id){
        bookRepository.deleteById(id);
        return "success";
    }

    @RequestMapping("get")
    public BookDO get(Long id){
        Optional<BookDO> byId = bookRepository.findById(id);
        return byId.get();
    }

    @RequestMapping("getAll")
    public Iterable<BookDO> getALL(List<Long> ids){
        Iterable<BookDO> bookDOS = bookRepository.findAllById(ids);
        return bookDOS;
    }
}
