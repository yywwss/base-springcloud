package com.zhwl.controller.book;


import com.zhwl.service.book.BookService;
import com.zhwl.bean.Book;
import com.zhwl.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody Book book){
        return ResultVo.ok(bookService.add(book));
    }

    //查询
    @GetMapping("getAll")
    public ResultVo getAll(){
        return ResultVo.ok(bookService.getAll());
    }
}
