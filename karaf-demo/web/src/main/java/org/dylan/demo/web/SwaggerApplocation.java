package org.dylan.demo.web;

import io.swagger.jaxrs.config.BeanConfig;
import org.dylan.demo.base.BasicApplication;

public class SwaggerApplocation extends BasicApplication {

    public SwaggerApplocation() {
        super("org.dylan.demo.web");

        // 构建Swagger的配置对象
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8002");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("org.dylan.demo");
        beanConfig.setScan(true);
    }

}
