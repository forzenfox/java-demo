package org.dylan.demo.impl;

import org.dylan.demo.api.ITest;
import org.dylan.demo.api.annotations.Log;

public class TestImpl implements ITest {

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
