package com.zhwl.feignservice;

import com.zhwl.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "base-stock")
public interface BookFeignService {
    @GetMapping("book/getAll")
    ResultVo getAll();
}
