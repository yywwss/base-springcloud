package com.zhwl.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhwl.bean.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> getAll();
}
