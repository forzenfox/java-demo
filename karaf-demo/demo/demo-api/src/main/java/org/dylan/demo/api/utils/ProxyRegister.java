package org.dylan.demo.api.utils;

import org.dylan.demo.api.proxy.LogProxy;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import java.util.Dictionary;
import java.util.Hashtable;

public class ProxyRegister {

    public static <T> void registerProxyService(T service) {
        BundleContext bundleContext = FrameworkUtil.getBundle(ProxyRegister.class).getBundleContext();

        Dictionary<String, Object> properties = new Hashtable<>(1);
        properties.put("type", "proxy");

        // 默认注册 被代理类实现的第一个接口
        bundleContext.registerService(service.getClass().getInterfaces()[0].getName(),
                LogProxy.createProxy(service), properties);

    }

    // public static <T> void AutoRegisterProxyService(String packageName) {
    //     BundleContext bundleContext = FrameworkUtil.getBundle(ProxyRegister.class).getBundleContext();
    //
    //     try {
    //         Set<Class> classSet = ClassScanUtil.getClass4Annotation(packageName);
    //         // 遍历 获取带自定义注解的方法
    //         classSet.forEach(c -> {
    //             Method[] methods = c.getMethods();
    //
    //             for (Method method : methods) {
    //                 if (Objects.nonNull(method.getAnnotation(Log.class))) {
    //                     bundleContext.getService(bundleContext.getServiceReference(c));
    //                     bundleContext.registerService(c.getInterfaces()[0], LogProxy.createProxy());
    //                 }
    //             }
    //         });
    //     } catch (IOException | ClassNotFoundException e) {
    //         e.getStackTrace();
    //     }
    // }
}
