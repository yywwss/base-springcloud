package com.zhwl.controller.order;

import com.zhwl.bean.Order;
import com.zhwl.bean.SysUser;
import com.zhwl.exception.BaseException;
import com.zhwl.result.ResultVo;
import com.zhwl.service.OrderService;
import com.zhwl.util.SysUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("getAll")
    public ResultVo getAll(){
        return ResultVo.ok(orderService.getAll());
    }

    @PostMapping
    public ResultVo add(@RequestBody Order order){
        SysUser currentUser = SysUserUtil.getCurrentUser();
        System.out.println("========"+currentUser);
        return ResultVo.ok(orderService.add(order));
    }
}
