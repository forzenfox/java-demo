package 类加载;

import VO.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/*
    Field getField(name)：根据字段名获取某个public的field（包括父类）
    Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）
    Field[] getFields()：获取所有public的field（包括父类）
    Field[] getDeclaredFields()：获取当前类的所有field（不包括父类）
 */
public class 通过反射获取字段 {
    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.name = "xiao ming";

        Class sClass = student.getClass();
        // Class sClass = Class.forName(Student.class.getCanonicalName());

        System.out.println(sClass.getField("score"));
        System.out.println(sClass.getField("name"));

        System.out.println(sClass.getDeclaredField("grade"));

        获取字段信息(sClass.getField("name"));


        // 获取字段值
        // 通过Field实例可以读取或设置某个对象的字段，如果存在访问限制，要首先调用setAccessible(true)来访问非public字段
        System.out.println(sClass.getField("name").get(student));

        // 设置字段值
        sClass.getField("name").set(student, "xiao hong");
        System.out.println(sClass.getField("name").get(student));
    }

    /*
        getName()：返回字段名称，例如，"name"；
        getType()：返回字段类型，也是一个Class实例，例如，String.class；
        getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。
     */
    private static void 获取字段信息(Field f) {
        System.out.println(f.getName());
        System.out.println(f.getType());

        int m = f.getModifiers();
        System.out.println(m);
        System.out.println(Modifier.isAbstract(m));
        System.out.println(Modifier.isFinal(m));
        System.out.println(Modifier.isInterface(m));
        System.out.println(Modifier.isNative(m));
        System.out.println(Modifier.isPrivate(m));
        System.out.println(Modifier.isProtected(m));
        System.out.println(Modifier.isPublic(m));

    }
}
