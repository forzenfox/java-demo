package 类加载;

import VO.Person;

import java.lang.reflect.Constructor;

/*
    调用非public的Constructor时，必须首先通过setAccessible(true)设置允许访问。setAccessible(true)可能会失败
    getConstructor(Class...)：获取某个public的Constructor；
    getDeclaredConstructor(Class...)：获取某个Constructor；
    getConstructors()：获取所有public的Constructor；
    getDeclaredConstructors()：获取所有Constructor。
 */
public class 通过反思获取构造函数 {
    public static void main(String[] args) throws Exception {
        // Class.newInstance() 只能调用该类的public无参数构造方法。
        Person p = Person.class.newInstance();

        // 获取构造方法Integer(int):
        Constructor cons1 = Integer.class.getConstructor(int.class);
        // 调用构造方法:
        Integer n1 = ( Integer ) cons1.newInstance(123);
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = ( Integer ) cons2.newInstance("456");
        System.out.println(n2);

    }
}
