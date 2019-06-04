package com.zhwl.feign;

import com.zhwl.service.BookService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 通过测试发现调用Feign不经过网关，也就是说不可以通过服务别名访问
 */
@FeignClient(value = "base-stock-impl",path = "/book")
public interface BookServiceFeign extends BookService {

}
