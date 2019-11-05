package com.zhwl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * 注意：由于项目中引入依赖springfox-spring-web版本号和swagger不一致，导致本模块启动失败
 * 解决1：去除依赖springfox-spring-web，但未能解决问题，不知为什么
 * 解决2：保持版本一致
 */
@Configuration
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("base-stock", "/api-stock/v2/api-docs"));
        resources.add(swaggerResource("base-order", "/api-order/v2/api-docs"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
