package 设计模式.动态代理.CGLIB;

import net.sf.cglib.core.DebuggingClassWriter;

public class Test {

    public static void main(String[] args) {
        // 设置CGLIB的字节码文件存放路径
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\代码\\git-demo\\jdk-demo\\target");

        TestCGLIBProxy.creatProxy().sayHello();
    }

    public String sayHello() {
        return "hello world!";
    }
}
