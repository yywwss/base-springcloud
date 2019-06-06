package com.zhwl.service;

import com.zhwl.bean.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookService {

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    Integer add(@RequestBody Book book);

    @PutMapping()
    Integer update(@RequestBody Book book);

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    List<Book> getAll();

    @RequestMapping(value="getById",method = RequestMethod.GET)
    Book getById(@RequestParam(value = "id") String id);

    @RequestMapping(value="getByName",method = RequestMethod.GET)
    List<Book> getByName(@RequestParam(value = "name") String name);

    @PutMapping(value="reduceBook")
    void reduceBook(@RequestParam(value = "bookId") String bookId, @RequestParam(value = "count")Integer count);
}
