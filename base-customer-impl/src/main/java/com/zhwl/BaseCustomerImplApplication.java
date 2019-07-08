package com.zhwl;

import com.zhwl.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,ManagementWebSecurityAutoConfiguration.class})
@EnableEurekaClient
@EnableDistributedTransaction
//basePackages 一定要加，不然会启动报错，加的这个是Feign服务客户端包名
@EnableFeignClients(basePackages = {"com.zhwl.feign"})
public class BaseCustomerImplApplication {

    public static void main(String[] args) {

        SpringApplication.run(BaseCustomerImplApplication.class, args);
    }

}
