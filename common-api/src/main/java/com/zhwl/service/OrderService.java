package com.zhwl.service;

import com.zhwl.bean.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface OrderService {

    @PostMapping
    Integer add(@RequestBody Order order);

    @RequestMapping
    List<Order> getAll();
}
