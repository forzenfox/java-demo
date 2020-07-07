package org.dylan.demo.impl.init;

import org.dylan.demo.api.utils.ProxyRegister;
import org.dylan.demo.impl.TestImpl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DemoProvider {

    @Inject
    public DemoProvider() {
    }

    @PostConstruct
    public void init() {
        ProxyRegister.registerProxyService(new TestImpl());
    }

}
