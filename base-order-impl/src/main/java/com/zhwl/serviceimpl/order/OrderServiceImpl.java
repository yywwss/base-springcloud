package com.zhwl.serviceimpl.order;

import com.zhwl.bean.Book;
import com.zhwl.bean.Order;
import com.zhwl.exception.BaseException;
import com.zhwl.feign.BookServiceFeign;
import com.zhwl.mapper.order.OrderMapper;
import com.zhwl.service.OrderService;
import com.zhwl.txlcn.tc.annotation.LcnTransaction;
import com.zhwl.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookServiceFeign bookServiceFeign;

    @LcnTransaction
    @Override
    public Integer add(Order order) {

        //提交订单
        orderMapper.insert(order.setId(UuidUtil.get32UUID()));

        //减少对应库存
        bookServiceFeign.reduceBook(order.getBookId(),order.getCount());
        if (order.getCount()>10)
            throw new BaseException("未知错误");

        return 1;
    }

    @Override
    public List<Order> getAll() {
        System.out.println("mybatisPlus");
        return orderMapper.getAll();
    }
}
