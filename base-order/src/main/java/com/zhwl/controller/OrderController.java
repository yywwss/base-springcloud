package com.zhwl.controller;

import com.zhwl.feign.BookServiceFeign;
import com.zhwl.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private BookServiceFeign bookServiceFeign;


    @GetMapping("getAllBook")
    public ResultVo getAllBook(){
        return ResultVo.ok(bookServiceFeign.getAll());
    }
}
