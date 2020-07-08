package org.dylan.demo.impl;

import org.apache.aries.blueprint.annotation.service.Service;
import org.dylan.demo.api.ITest;
import org.dylan.demo.api.annotations.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Service
public class TestImpl implements ITest {

    @Inject
    public TestImpl() {
    }

    @Log(operation = "测试代码")
    @Override
    public String test() {
        return "hello world!";
    }

    @Override
    public String test01() {
        return "无log注解";
    }
}
