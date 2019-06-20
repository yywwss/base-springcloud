package com.zhwl.controller.book;


import com.zhwl.bean.Book;
import com.zhwl.exception.BaseException;
import com.zhwl.feign.BookServiceFeign;
import com.zhwl.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookServiceFeign bookServiceFeign;
    //private BookService bookService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody Book book){
        return ResultVo.ok(bookServiceFeign.add(book));
    }

    //修改
    @PutMapping
    public ResultVo update(@RequestBody Book book){
        return ResultVo.ok(bookServiceFeign.update(book));
    }

    //查询
    @GetMapping("getAll")
    public ResultVo getAll(){
        return ResultVo.ok(bookServiceFeign.getAll());
    }

    @GetMapping("getByName")
    public ResultVo getByName(@RequestParam(value = "name") String name){
        return ResultVo.ok(bookServiceFeign.getByName(name));
    }
}
