package JDK8.Stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class CollectorsDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("jack", "bob", "alice", "mark");
        List<String> duplicateList = Arrays.asList("jack", "jack", "alice", "mark");
        
        // Collectos.toList() 转化集合为ArrayList
        List<String> listResult = list.stream().collect(Collectors.toList());
        log.info("结果:{}", listResult);
        
        // Collectos.toSet() 转化集合为HashSet
        Set<String> setResult = list.stream().collect(Collectors.toSet());
        log.info("结果:{}", setResult);
        Set<String> setResult1 = duplicateList.stream().collect(Collectors.toSet());
        log.info("结果:{}", setResult1);
        
        DemoToCollection(list);
        
        DemoToMap(list, duplicateList);
        
        //  Collectors.collectingAndThen 生成的集合再做一次操作
        String str = list.stream()
                .collect(Collectors.collectingAndThen(Collectors.joining(","), v -> v + "aaa"));
        log.info("{}", str);
        
        DemoJoin(list);
        
        DemoCompute();
    }
    
    private static void DemoCompute() {
        // 求最值 3
        List<Integer> nums = Arrays.asList(1, 2, 3);
        Integer maxValue = nums.stream().collect(Collectors.collectingAndThen(Collectors.maxBy((a, b) -> a - b), Optional::get));
        
        // 最小值 1
        Integer minValue = nums.stream().collect(Collectors.collectingAndThen(Collectors.minBy((a, b) -> a - b), Optional::get));
        
        // 求和 6
        Integer sumValue = nums.stream().collect(Collectors.summingInt(item -> item));
        
        // 平均值 2.0
        Double avgDouble = nums.stream().collect(Collectors.averagingDouble(x -> x));
        Double avgInt = nums.stream().collect(Collectors.averagingInt(x -> x));
        log.info("最大:{} 最小:{} 求和:{} 平均:{},{}", maxValue, minValue, sumValue, avgDouble, avgInt);
    }
    
    /**
     * Collectors.joining() 拼接集合元素
     *
     * @param list
     */
    private static void DemoJoin(List<String> list) {
        // 等效 log.info("{}", String.join("", list));
        log.info("{}", list.stream().collect(Collectors.joining()));
        
        // 等效 log.info("{}", String.join("-", list));
        log.info("{}", list.stream().collect(Collectors.joining("-")));
        log.info("{}", list.stream().collect(Collectors.joining("-", "start[", "]end")));
    }
    
    /**
     * Collectors.toCollection() 转化为指定集合类型
     *
     * @param list
     */
    private static void DemoToCollection(List<String> list) {
        LinkedList<String> linkedList = list.stream().collect(Collectors.toCollection(LinkedList::new));
        log.info("LinkedList:{}", linkedList instanceof LinkedList);
        
        CopyOnWriteArrayList<String> copy = list.stream().collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        log.info("CopyOnWriteArrayList:{}", copy instanceof CopyOnWriteArrayList);
        
        TreeSet<String> treeSet = list.stream().collect(Collectors.toCollection(TreeSet::new));
        log.info("TreeSet:{},数据:{}", treeSet instanceof TreeSet, treeSet);
        
    }
    
    /**
     * Collectors.toMap() 转化为map
     *
     * @param list
     * @param duplicateList
     */
    private static void DemoToMap(List<String> list, List<String> duplicateList) {
        //
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(Function.identity(), String::hashCode));
        log.info("结果:{}", map);
        // 处理Key重复的情况
        Map<String, Integer> map1 = duplicateList.stream()
                .collect(Collectors.toMap(Function.identity(), String::hashCode, Integer::sum));
        log.info("结果:{}", map1);
        // 自定义Map类型
        Map<String, Integer> map2 = list.stream().collect(Collectors.toMap(Function.identity(), String::hashCode,
                (v1, v2) -> v1, ConcurrentHashMap::new));
        log.info("ConcurrentHashMap:{}", map2 instanceof ConcurrentHashMap);
    }
}
