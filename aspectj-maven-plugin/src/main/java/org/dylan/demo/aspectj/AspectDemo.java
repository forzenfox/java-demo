package org.dylan.demo.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectDemo {

    @Pointcut("execution(* org.dylan.demo.aspectj.App.say())")
    private void pointcut() {
    }  // signature

    @Before("pointcut()")
    public void before() {
        System.out.println("Hello");
    }
}
