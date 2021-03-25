package 集合;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMapDemo {
    public static void main(String[] args) {
        Comparator<A> ACpt = (a, b) -> StringUtils.compare(a.getA(), b.getA(), false);

        Set<A> aSet = new TreeSet<>(ACpt);
        aSet.add(new A("小红"));
        aSet.add(new A("阿芳"));
        aSet.add(new A("小兰"));
        aSet.add(new A("张兰"));
        System.out.println(aSet);

        Set<B> bSet = new TreeSet<>();
        bSet.add(new B("小红"));
        bSet.add(new B("阿芳"));
        bSet.add(new B("小兰"));
        bSet.add(new B("张兰"));
        System.out.println(bSet);

        //根据key值排序 默认排序 或者 自定义排序
        Map<String, Integer> map = new TreeMap<>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
        // apple, orange, pear
    }
}

// 未实现Compareable接口
class A {
    private String a;

    public A(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", A.class.getSimpleName() + "[", "]")
                .add("a='" + a + "'")
                .toString();
    }
}

//实现Compareable接口
class B implements Comparable {
    private String b;

    public B(String b) {
        this.b = b;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", B.class.getSimpleName() + "[", "]")
                .add("b='" + b + "'")
                .toString();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof B) {
            return StringUtils.compare(b, (( B ) o).getB(), false);
        }
        return -1;
    }
}