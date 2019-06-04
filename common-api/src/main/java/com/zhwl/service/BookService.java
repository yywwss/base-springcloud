package com.zhwl.service;

import com.zhwl.bean.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookService {

    @RequestMapping(method = RequestMethod.POST)
    Integer add(Book book);

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    List<Book> getAll();

    @RequestMapping(value="getByName",method = RequestMethod.GET)
    List<Book> getByName(@RequestParam(value = "name") String name);
}
