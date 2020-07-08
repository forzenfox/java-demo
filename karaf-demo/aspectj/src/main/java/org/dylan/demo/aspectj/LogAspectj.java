package org.dylan.demo.aspectj;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.dylan.demo.annotations.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspectj {
    private static final Logger log = LoggerFactory.getLogger(LogAspectj.class);

    // 定义切面
    @Pointcut("execution(@org.dylan.demo.annotations.Log  * *..*.*(..)) && @annotation(logAnno)")
    public void pointCut(Log logAnno) {

    }

    @Before("pointCut(logAnno)")
    public void before(Log logAnno) {
        log.info("运行前 执行增强代码" + logAnno.operation());
    }

    @After("pointCut(logAnno)")
    public void after(Log logAnno) {
        log.info("运行后 执行增强代码" + logAnno.operation());
    }
}
