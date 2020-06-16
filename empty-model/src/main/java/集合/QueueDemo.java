package 集合;

import java.util.LinkedList;
import java.util.Queue;

/*
    int size()：获取队列长度；
    boolean add(E)/boolean offer(E)：添加元素到队尾；
    E remove()/E poll()：获取队首元素并从队列中删除；
    E element()/E peek()：获取队首元素但并不从队列中删除。
 */
public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();

        q.add("123");// 失败时抛出异常
        q.offer("abc");// 失败时返回false 不会抛出异常


        System.out.println(q.element());//失败时抛出异常
        System.out.println(q.peek());//失败时返回false

        q.remove();//失败时抛出异常
        q.poll();//失败时返回false

        q.size();

        q.offer("6");
        q.offer("2");
        q.offer("3");
        q.offer("1");
        q.offer("5");
        q.offer("4");
        System.out.println(q.toString());

        // 遍历的顺序按照add的顺序
        // q.forEach(System.out :: println);

        do {
            System.out.println(q.poll());
        } while (q.size() > 0);
    }
}
