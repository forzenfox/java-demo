package 设计模式.动态代理.CGLIB;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] orgs, MethodProxy methodProxy) throws Throwable {
        System.out.println("before execute ...");
        Object result = methodProxy.invokeSuper(o, orgs);
        System.out.println("after execute ...");
        return result;
    }
}
