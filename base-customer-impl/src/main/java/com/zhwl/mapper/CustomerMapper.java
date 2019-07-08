package com.zhwl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhwl.bean.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
    Customer loadUserByUsername(String username);
}
