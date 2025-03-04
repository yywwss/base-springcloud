package com.zhwl;

import com.zhwl.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction
public class BaseStockImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseStockImplApplication.class, args);
    }

}
