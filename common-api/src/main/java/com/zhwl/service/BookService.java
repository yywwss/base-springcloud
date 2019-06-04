package com.zhwl.service;

import com.zhwl.bean.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookService {

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping()
    Integer add(@RequestBody Book book);

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    List<Book> getAll();

    @RequestMapping(value="getByName",method = RequestMethod.GET)
    List<Book> getByName(@RequestParam(value = "name") String name);
}
