package 设计模式.动态代理.CGLIB;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

public class TestFixedValue {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback(( FixedValue ) () -> "test fixed value!");
        Test testProxy = ( Test ) enhancer.create();

        System.out.println(testProxy.sayHello());
        System.out.println(testProxy.toString());
        System.out.println(testProxy.getClass());// final 方法无法继承，因此无法拦截
        System.out.println(testProxy.hashCode());// 报错因为hashCode()回参是int,方法拦截后返回的是String
    }
}
