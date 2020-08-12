package org.dylan.demo.base;

import com.sun.jersey.api.core.PackagesResourceConfig;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.slf4j.LoggerFactory;

public class BasicApplication extends PackagesResourceConfig {

    public BasicApplication(String resourcePackageName) {
        super(resourcePackageName, SwaggerSerializers.class.getPackage().getName());

        LoggerFactory.getLogger(this.getClass()).info("{} register api success", this.getClass().getSimpleName());
    }
}
