package 设计模式.动态代理;

public class testMain {
    public static void main(String[] args) {
        ITest proxy = LogProxy.createProxy(new TestImpl());
        proxy.test();
    }
}
