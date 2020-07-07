package org.dylan.demo.rest;

import com.inspur.ice.sys.framework.BasicApplication;
import org.dylan.demo.rest.resources.TestResources;

public class RestApplication extends BasicApplication {
    public RestApplication() {
        super(TestResources.class.getPackage().getName());
    }
}
