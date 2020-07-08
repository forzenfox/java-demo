package org.dylan.demo.aspectj;

public class LogDemo {

    @Log
    public void test() {
        System.out.println("Hello World!");
    }
}
