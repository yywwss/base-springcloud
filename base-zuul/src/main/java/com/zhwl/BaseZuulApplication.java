package com.zhwl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
//basePackages 一定要加，不然会启动报错，加的这个是Feign服务客户端包名
@EnableFeignClients(basePackages = {"com.zhwl.feign"})
public class BaseZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseZuulApplication.class, args);
    }

}
