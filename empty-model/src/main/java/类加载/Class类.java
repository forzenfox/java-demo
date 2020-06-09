package 类加载;

/*
    1.JVM动态加载 在第一次使用对应类的时候 才会加载对应class/interface的class文件
    2.JVM 加载class文件后会创建一个Class类型的实例，存储了对应类的所有信息
    3.获取Class类型实例的方法：
        <1>. Class.forName("完整类名") 例: Class c = Class.forName("java.lang.String");
        <2>. 通过class的静态变量class 例：Class c = String.class
        <3>. 通过class的实例的getClass方法 例：Class c = "hellow".getClass();
    4.反射：通过Class实例获取对应class/interface信息的方法
 */

import VO.Student;

public class Class类 {
    public static void main(String[] args) {

        System.out.println("inside main");
        new Candy();
        System.out.println("after creating Candy");

        /*
         * 1.Gum.class 不会触发JVM加载CLass文件
         * 2.Class.forName() 只会触发JVM加载Class文件，不会构造对应class的实例
         */

        // Gum.class.getCanonicalName();
        try {
            Class.forName(Gum.class.getCanonicalName());
        } catch (ClassNotFoundException e) {
            System.out.println("couldn`t find Gum");
        }

        System.out.println("after Class.forname Gum");
        new Cookie();
        System.out.println("after creating cookie");

        // 获取父类的Class
        Class p = Student.class.getSuperclass();

        // 获取interface 只返回当前类直接实现的接口类型，并不包括其父类实现的接口类型
        // 如果一个类没有实现任何interface，那么getInterfaces()返回空数组
        Student.class.getInterfaces();

        //  判断一个向上转型是否成立，可以调用isAssignableFrom()
        // Integer i = ?
        Integer.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Integer
        // Number n = ?
        Number.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Number
        // Object o = ?
        Object.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Object
        // Integer i = ?
        Integer.class.isAssignableFrom(Number.class); // false，因为Number不能赋值给Integer
    }


}

class Candy {
    static {
        System.out.println("Loading Candy");
    }

    Candy() {
        System.out.println("构造 Candy 实例");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }

    Gum() {
        System.out.println("构造 Gum 实例");
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
    }

    Cookie() {
        System.out.println("构造 Cookie 实例");
    }
}
