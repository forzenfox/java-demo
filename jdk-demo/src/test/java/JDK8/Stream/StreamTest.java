package JDK8.Stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamTest {
    
    /**
     * 测试null对象的Stream方法是否报空指针
     */
    @Test
    public void nullOfStream() {
        List<String> a = null;
        Assert.assertThrows(NullPointerException.class, () -> a.stream().findFirst().isPresent());
    }
    
    @Test
    public void creatTest() {
        Stream.empty().forEach(System.out::print);
        System.out.println(" ");
        Stream.of("a").forEach(System.out::print);
        System.out.println(" ");
        Stream.of("a", "b", "c").forEach(System.out::print);
        System.out.println(" ");
        
        String[] array1 = {"a", "b", "c"};
        String[] array2 = {"a1", "b1", "c1"};
        Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).forEach(System.out::print);
    }
    
    @Test
    public void parallelTest() {
        List<String> list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("abc");
        list.add("ccc");
        list.add("ddd");
        list.parallelStream().forEach(log::info);
        list.stream().parallel().forEach(log::info);
    }
    
    @Test
    public void flatmapTest() {
        String[] array1 = {"a", "b", "c"};
        String[] array2 = {"a1", "b1", "c1"};
        // 流扁平化处理
        log.info("{}", Stream.of(array1, array2).flatMap(x -> Arrays.stream(x)).collect(Collectors.toList()));
    }
    
    @Test
    public void peekTest() {
        // 中间操作是lazy操作，并不会立马启动，需要等待终止操作才会执行
        Stream.of("a", "b", "c").peek(System.out::print);
        
        // toList 去触发Stream的执行
        Stream.of("a", "b", "c").peek(System.out::print).collect(Collectors.toList());
        
        // peek和map的区别 Consumer 无返回值  Function 有返回值
        log.info("{}", Stream.of("a", "b", "c").peek(String::toUpperCase).collect(Collectors.toList()));
        log.info("{}", Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.toList()));
        
        
    }
    
}
