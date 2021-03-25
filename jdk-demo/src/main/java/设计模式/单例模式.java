package 设计模式;

public class 单例模式 {
}

class Singleton {
    private static class Instance {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    public static Singleton getInstance() {
        return Instance.INSTANCE;
    }
}