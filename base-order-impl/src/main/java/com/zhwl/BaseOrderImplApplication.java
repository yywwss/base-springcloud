package com.zhwl;

import com.zhwl.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//basePackages 一定要加，不然会启动报错，加的这个是Feign服务客户端包名
@EnableFeignClients(basePackages = {"com.zhwl.feign"})
@EnableDistributedTransaction
public class BaseOrderImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseOrderImplApplication.class, args);
    }

}
