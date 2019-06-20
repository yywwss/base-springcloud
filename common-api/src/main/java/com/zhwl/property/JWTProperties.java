package com.zhwl.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component("jwtProperties")
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    private String header = "Authorization";
    private String secret = "MyJwtSecret";
    private Integer expiration = 3;
    private Integer sessionTimeout = 30;
    private String tokenHead = "Bearer";
}
