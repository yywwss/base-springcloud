package com.zhwl.service;

import com.zhwl.bean.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerService /*extends UserDetailsService*/ {

    @GetMapping(value = "getAll")
    List<Customer> getAll();

    @GetMapping(value = "loadUserByUsername")
    Customer loadUserByUsername(@RequestParam(name = "username") String username);
}
