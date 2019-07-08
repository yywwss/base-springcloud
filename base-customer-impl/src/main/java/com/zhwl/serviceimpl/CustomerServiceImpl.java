package com.zhwl.serviceimpl;


import com.zhwl.bean.Customer;
import com.zhwl.bean.SysUser;
import com.zhwl.mapper.CustomerMapper;
import com.zhwl.service.CustomerService;
import com.zhwl.util.SysUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@Service
@RestController
@RequestMapping("customer")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> getAll() {
        SysUser currentUser = SysUserUtil.getCurrentUser();
        System.out.println(currentUser);
        return customerMapper.selectList(null);
    }

    @Override
    public Customer loadUserByUsername(String username){
        return Optional.ofNullable(username).map(x->customerMapper.loadUserByUsername(x)).orElse(null);
    }
}
