package com.zhwl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient//本服务启动后会自动注册进eureka服务中
//basePackages 一定要加，不然会启动报错，加的这个是Feign服务客户端包名
@EnableFeignClients(basePackages = {"com.zhwl.feign"})
public class BaseStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseStockApplication.class, args);
    }

}
