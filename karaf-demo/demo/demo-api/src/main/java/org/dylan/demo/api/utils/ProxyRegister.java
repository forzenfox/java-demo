package org.dylan.demo.api.utils;

import org.dylan.demo.api.proxy.LogProxy;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class ProxyRegister {
    public static <T> void registerProxyService(T service) {
        BundleContext bundleContext = FrameworkUtil.getBundle(ProxyRegister.class).getBundleContext();
        bundleContext.registerService(service.getClass().getInterfaces()[0].getName(),
                LogProxy.createProxy(service),
                null);

        // try {
        //     Set<Class> classSet = ClassScanUtil.getClass4Annotation(packageName);
        //     // 遍历 获取带自定义注解的方法
        //     classSet.forEach(c -> {
        //         Method[] methods = c.getMethods();
        //
        //         for (Method method : methods) {
        //             if (Objects.nonNull(method.getAnnotation(Log.class))) {
        //
        //                 bundleContext.registerService(c.getInterfaces()[0], LogProxy.createProxy());
        //             }
        //         }
        //     });
        // } catch (IOException | ClassNotFoundException e) {
        //     e.getStackTrace();
        // }


    }
}
