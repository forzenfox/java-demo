package 泛型;

import javafx.util.Pair;

public class 泛型 {
    public static void main(String[] args) {
        Pair[] arr = new Pair[2];
        Pair<String, String>[] ps = ( Pair<String, String>[] ) arr;

        System.out.println(ps.getClass() == Pair[].class); // true
        System.out.println(ps.getClass());
    }
}
