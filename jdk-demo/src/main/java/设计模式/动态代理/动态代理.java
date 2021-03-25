package 设计模式.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class 动态代理 {
    public static void main(String[] args) {
        A aProxy = AProxy.creatProxy(new AImpl());

        aProxy.say();
    }
}

interface A {
    void say();
}

class AImpl implements A {
    @Override
    public void say() {
        System.out.println("hello word!");
    }
}

class AProxy implements InvocationHandler {
    private Object object;

    private AProxy(Object object) {
        this.object = object;
    }

    public static <T> T creatProxy(T o) {
        return ( T ) Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new AProxy(o));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("****before*****");
        return method.invoke(object, args);
    }
}
