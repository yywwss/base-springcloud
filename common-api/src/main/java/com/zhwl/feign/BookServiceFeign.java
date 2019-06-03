package com.zhwl.feign;

import com.zhwl.service.BookService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "base-stock-impl",path = "/book")
public interface BookServiceFeign extends BookService {

}
