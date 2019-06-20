package com.zhwl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:common.properties")  //指定common的配置文件路径
public class CommonProperties {
}
