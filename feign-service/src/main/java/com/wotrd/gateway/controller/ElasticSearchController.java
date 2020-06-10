//package com.wotrd.gateway.controller;
//
//import com.wotrd.gateway.model.entity.BookDO;
//import com.wotrd.gateway.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Optional;
//
//@RequestMapping("es")
//@RestController
//public class ElasticSearchController {
//
//    @Autowired
//    private BookService bookService;
//
//    @RequestMapping("createIndex")
//    public String createIndex(String indexName){
//        bookService.createIndex(indexName);
//        return "create success";
//    }
//
//    @RequestMapping("delete")
//    public String delete(String indexName){
//        bookService.delete(indexName);
//        return "delete success";
//    }
//
//    @RequestMapping("add")
//    public BookDO get(@RequestBody BookDO bookDO){
//        return bookService.save(bookDO);
//    }
//
//    @RequestMapping("deleteData")
//    public String delete(Long id){
//        bookService.deleteById(id);
//        return "success";
//    }
//
//    @RequestMapping("get")
//    public BookDO get(Long id){
//        Optional<BookDO> byId = bookService.findById(id);
//        return byId.get();
//    }
//
//    @RequestMapping("getAll")
//    public Iterable<BookDO> getALL(@RequestBody List<Long> ids){
//        Iterable<BookDO> bookDOS = bookService.findAllById(ids);
//        return bookDOS;
//    }
//
//    @RequestMapping("findAll")
//    public String findAll(){
//        bookService.findAll();
//        return "find all success";
//    }
//
//    @RequestMapping("queryBuilderSearch")
//    public String queryBuilderSearch(String name){
//        bookService.queryBuilderSearch(name);
//        return "find all success";
//    }
//
//
//
//
//
//
//}
