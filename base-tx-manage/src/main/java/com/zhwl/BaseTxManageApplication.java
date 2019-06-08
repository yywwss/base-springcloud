package com.zhwl;

import com.zhwl.support.TxLcnManagerBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseTxManageApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BaseTxManageApplication.class);
        springApplication.setBanner(new TxLcnManagerBanner());
        springApplication.run(args);
    }

}
