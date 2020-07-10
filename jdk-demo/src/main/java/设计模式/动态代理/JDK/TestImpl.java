package 设计模式.动态代理.JDK;

public class TestImpl implements ITest {

    @Log(operation = "测试代码")
    @Override
    public String test() {
        return "hello world!";
    }
}
