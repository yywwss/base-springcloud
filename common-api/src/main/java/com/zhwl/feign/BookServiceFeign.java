package com.zhwl.feign;

import com.zhwl.service.BookService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 通过测试发现调用Feign
 */
//不经过网关，也就是说不可以通过服务别名访问
@FeignClient(value = "base-stock-impl",path = "/book")

//经过网关，但是在分布式事务中存在问题，无法回滚。因为是通过微服务别名访问。
//@FeignClient(value = "base-zuul",path = "/api-stock-impl/book")
public interface BookServiceFeign extends BookService {

}
