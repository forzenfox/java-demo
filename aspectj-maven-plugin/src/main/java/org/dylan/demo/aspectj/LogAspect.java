package org.dylan.demo.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {

    @Pointcut(value = "execution(@org.dylan.demo.aspectj.Log  * *..*.*(..)) && @annotation(logAnno)")
    public void pointCut(Log logAnno) {

    }

    @Before(value = "pointCut(logAnno)", argNames = "logAnno")
    public void before(Log logAnno) {
        System.out.println("运行前 执行增强代码" + logAnno.operation());
    }

    @After(value = "pointCut(logAnno)", argNames = "logAnno")
    public void after(Log logAnno) {
        System.out.println("运行后 执行增强代码" + logAnno.operation());
    }

}
