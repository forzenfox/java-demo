package 设计模式.动态代理.CGLIB;

import net.sf.cglib.proxy.Enhancer;

public class TestCGLIBProxy {
    public static Test creatProxy() {
        Enhancer enhancer = new Enhancer();
        // 设置被代理类
        enhancer.setSuperclass(Test.class);
        // 设置方法拦截器
        enhancer.setCallback(new TestMethodInterceptor());
        return ( Test ) enhancer.create();
    }
}
