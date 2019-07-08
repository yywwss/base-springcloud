package com.zhwl.feign;

import com.zhwl.service.CustomerService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "base-customer-impl",path = "/customer")
public interface CustomerServiceFeign extends CustomerService {
}
