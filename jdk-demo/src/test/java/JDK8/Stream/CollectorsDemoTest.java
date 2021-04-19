package JDK8.Stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CollectorsDemoTest {
    private final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9);
    
    @Test
    public void mappingTest() {
        log.info("mapping:{}", Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toLowerCase(), Collectors.joining())));
        log.info("mapping2:{}", Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toLowerCase(), Collectors.toList())));
    }
    
    @Test
    public void groupByTest() {
        // Collectors.groupingBy 可指定mapFactory 和 downstream 默认HashMap和 Collectors.toList()
        Map<Integer, List<Integer>> mapToList = list.stream().collect(Collectors.groupingBy(e -> e % 3));
        Map<Integer, Set<Integer>> mapToSet = list.stream().collect(Collectors.groupingBy(e -> e % 3,
                Collectors.toCollection(TreeSet::new)));
        Map<Integer, Set<Integer>> map = list.stream().collect(Collectors.groupingBy(e -> e % 3, TreeMap::new,
                Collectors.toCollection(TreeSet::new)));
        log.info("mapToList:{}", mapToList);
        log.info("mapToSet:{}", mapToSet);
        log.info("type:{},map:{}", map.getClass(), map);
        
        // eg: 先按奇偶分组 再按%3余数分组
        Map<Integer, Map<Integer, List<Integer>>> result = list.stream().collect(Collectors.groupingBy(e -> e % 2,
                Collectors.groupingBy(e -> e % 3)));
        log.info("result:{}", result);
        
        // eg: 先按奇偶分组 再按是否3的倍数分组
        Map<Integer, Map<Boolean, List<Integer>>> result1 = list.stream().collect(Collectors.groupingBy(e -> e % 2,
                Collectors.partitioningBy(e -> e % 3 == 0)));
        log.info("result:{}", result1);
        
        // eg: 先按奇偶分组 再计算数量
        Map<Integer, Long> result2 = list.stream().collect(Collectors.groupingBy(e -> e % 2, Collectors.counting()));
        log.info("result:{}", result2);
        
    }
    
    @Test
    public void groupByConcurrentTest() {
        // Collectors.groupingBy 可指定mapFactory 和 downstream 默认HashMap和 Collectors.toList()
        Map<Integer, List<Integer>> mapToList = list.stream().collect(Collectors.groupingByConcurrent(e -> e % 3));
        Map<Integer, Set<Integer>> mapToSet = list.stream().collect(Collectors.groupingByConcurrent(e -> e % 3,
                Collectors.toCollection(TreeSet::new)));
        Map<Integer, Set<Integer>> map = list.stream().collect(Collectors.groupingByConcurrent(e -> e % 3,
                ConcurrentSkipListMap::new, Collectors.toCollection(TreeSet::new)));
        log.info("mapToList:{}", mapToList);
        log.info("mapToSet:{}", mapToSet);
        log.info("type:{},map:{}", map.getClass(), map);
    }
    
    
    @Test
    public void partitioningByTest() {
        //  partitioningBy 把集合按规则分成两组; 可指定value的集合类型
        Map<Boolean, List<Integer>> mapToList = list.stream().collect(Collectors.partitioningBy(e -> e % 2 == 0));
        Map<Boolean, Set<Integer>> mapToSet = list.stream().collect(Collectors.partitioningBy(e -> e % 2 == 0, Collectors.toSet()));
        
        
        log.info("mapToList:{}", mapToList);
        log.info("{};mapToSet:{}", mapToSet.getClass(), mapToSet);
    }
    
    @Test
    public void reducingTest() {
        // Stream.reduce(BinaryOperator)
        log.info("reduce1:{}", list.stream().reduce(Integer::sum));
        log.info("reduce1:{}", list.stream().collect(Collectors.reducing(Integer::sum)));
        
        // reduce(T identity, BinaryOperator<T> accumulator);
        // identity: 必须满足accumulator.apply(identity, t) == t
        log.info("reduce2:{}", list.stream().reduce(0, Integer::sum));
        log.info("reduce2:{}", list.stream().collect(Collectors.reducing(0, Integer::sum)));
        
        // combiner用来合并多线程计算的结果
        // identity:  需要满足combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
        log.info("reduce3:{}", list.parallelStream().reduce(0, Integer::sum, Integer::sum));
        
        
        log.info("reduce3:{}", list.stream().collect(Collectors.reducing(0, x -> x + 1, Integer::sum)));
        log.info("reduce3:{}", list.stream().map(x -> x + 1).reduce(0, Integer::sum));
        
        
        int a = list.stream().collect(Collectors.collectingAndThen(Collectors.reducing(Integer::sum), Optional::get));
        log.info("{}", a);
    }
    
}