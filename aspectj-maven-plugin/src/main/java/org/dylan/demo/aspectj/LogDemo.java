package org.dylan.demo.aspectj;

public class LogDemo {

    @Log
    public void test() {
        System.out.println("Hello World!");
    }

    public void test1() {
        test();
        System.out.println("no OK");
    }
}
