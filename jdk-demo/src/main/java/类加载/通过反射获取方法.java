package 类加载;

import VO.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
    Method getMethod(name, Class...)：获取某个public的Method（包括父类）
    Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）
    Method[] getMethods()：获取所有public的Method（包括父类）
    Method[] getDeclaredMethods()：获取当前类的所有Method（不包括父类）
 */
public class 通过反射获取方法 {
    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.name = "xiao ming";
        Class stdClass = student.getClass();


        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("getScore", String.class));
        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int:
        System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));

        获取方法信息(stdClass.getMethod("getName"));

        //  调用方法
        调用方法(student, stdClass);


    }


    private static void 调用方法(Student student, Class stdClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method m = stdClass.getDeclaredMethod("getGrade", int.class);

        // 调用非public方法时 通过Method.setAccessible(true)允许其调用
        m.setAccessible(true);

        Object grade = m.invoke(student, 11);
        System.out.println(grade);

        // 多态：子类复写父类方法时 总是调用实际类型的覆写方法
        m = stdClass.getMethod("hello");
        m.invoke(student);
    }

    /*
        getName()：返回方法名称，例如："getScore"；
        getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
        getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
        getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。
     */
    private static void 获取方法信息(Method method) {
        System.out.println(method.getName());
        System.out.println(method.getReturnType());
        System.out.println(method.getParameterTypes());
        System.out.println(method.getModifiers());
    }
}
