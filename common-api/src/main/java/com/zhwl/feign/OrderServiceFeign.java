package com.zhwl.feign;

import com.zhwl.service.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "base-order-impl",path = "/order")
public interface OrderServiceFeign extends OrderService {
}
