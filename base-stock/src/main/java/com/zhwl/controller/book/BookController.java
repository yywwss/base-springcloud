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
        bookServiceFeign.add(book);
        if (true)
            throw new BaseException(0,"出现异常了");
        return ResultVo.ok();
        //return ResultVo.ok(bookService.add(book));
    }

    //查询
    @GetMapping("getAll")
    public ResultVo getAll(){
        return ResultVo.ok(bookServiceFeign.getAll());
        /*try {
            return ResultVo.ok(bookServiceFeign.getAll());
            //return ResultVo.ok(bookService.getAll());
        }catch (BaseException e){
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }*/
    }

    @GetMapping("getByName")
    public ResultVo getByName(@RequestParam(value = "name") String name){
        try {
            return ResultVo.ok(bookServiceFeign.getByName(name));
        }catch (BaseException e){
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
}
