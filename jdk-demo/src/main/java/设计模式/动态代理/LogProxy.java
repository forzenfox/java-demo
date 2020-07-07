package 设计模式.动态代理;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LogProxy implements InvocationHandler {
    private Object o;

    private LogProxy(Object o) {
        this.o = o;
    }

    public static <T> T createProxy(T obj) {
        return ( T ) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new LogProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //从实际的类对象中查找到所调用的具体方法，用来检测实际类的方法上的注解
        //否则需要把注解加载接口的方法上
        Method[] methods = o.getClass().getMethods();
        List<Class<?>> types = new ArrayList<Class<?>>();
        Method mm = null;
        //通过反射查找方法
        if (args == null || args.length == 0) {
            mm = o.getClass().getMethod(method.getName(), null);
        } else {
            for (Object oo : args) {
                types.add(oo.getClass());
            }
            mm = o.getClass().getMethod(method.getName(), ( Class<?>[] ) types.toArray());
        }
        System.out.println("代理启动");
        //检查注解类型
        // Annotation[] annos = mm.getAnnotations();
        // for (Annotation anno : annos) {
        //     if (anno instanceof Log) {
        //         Log tem = ( Log ) anno;
        //         System.out.println("探测到了LogTag注解，并且发现注解的level值为：" + tem.operation());
        //     }
        // }

        Annotation anno = mm.getAnnotation(Log.class);
        if (Objects.nonNull(anno)) {
            Log tem = ( Log ) anno;
            System.out.println("探测到了LogTag注解，并且发现注解的level值为：" + tem.operation());
        }

        //调用具体的方法，执行业务逻辑
        Object result = method.invoke(o, args);
        System.out.println("代理结束");
        return result;
    }
}
