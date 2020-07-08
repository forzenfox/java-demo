package org.dylan.demo.aspectj;

public class App {

    public static void main(String[] args) {
        // System.out.println(new App().say());
        new LogDemo().test();
    }

    public String say() {
        return "World";
    }
}