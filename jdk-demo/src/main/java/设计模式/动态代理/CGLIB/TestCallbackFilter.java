package 设计模式.动态代理.CGLIB;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

public class TestCallbackFilter {
    public static void main(String[] args) {
        CallbackHelper callbackHelper = new CallbackHelper(Test.class, null) {

            @Override
            protected Object getCallback(Method method) {
                // 根据条件返回不同的 Callback
                if (method.getName().equals("sayHello")) {
                    return ( FixedValue ) () -> "test fixed value!";
                }
                return NoOp.INSTANCE;
            }
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        Test testProxy = ( Test ) enhancer.create();
        System.out.println(testProxy.sayHello());
        System.out.println(testProxy.toString());

    }
}
