package 多线程;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Thread.NORM_PRIORITY;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /*
         ThreadPoolExecutor 构造函数参数：
            corePoolSize: 核心线程数，逐步增加；线程数大于等于核心线程数后，线程释放后最小也是等于核心线程数
            maximumPoolSize: 线程池维护线程的最大线程数
            keepAliveTime: 线程池维护线程所允许的空闲时间;不影响核心线程的生命周期
            unit: 程池维护线程所允许的空闲时间的单位
            workQueue: 线程池所使用的缓冲队列
                SynchronousQueue: 直接提交
                LinkedBlockingQueue: 无界队列 有无参构造函数，默认队列大小Integer.MAX_VALUE 若指定了队列大小，则为有界队列
                ArrayBlockingQueue: 有界队列
            handler: 线程池对拒绝任务的处理策略
            ThreadFactory: 创建新线程使用的模板
         */

        List<Runnable> tasks = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            i++;
            Runnable task = new NameTask("task" + i);
            tasks.add(task);
        }

        // 三种缓冲队列的使用场景
        // workQueueDemo(tasks);

        // 默认的ThreadFactory 和实现自定义ThreadFactory
        // ThreadFactoryDemo();

        // 拒绝策略
        // RejectedExecutionHandlerDemo(tasks);

        //JDK 提供的四种默认的线程池实现

        // 无核心线程 maximumPoolSize=Integer.MAX_VALUE 默认使用立即提交队列 60s回收空闲线程
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 固定线程数的线程池 核心线程数==maximumPoolSize 默认使用无界队列
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

        // 固定一个线程,默认线程不回收，并且使用无界队列
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

    }

    /*
        JDK提供四种预定义的处理程序策略：
            AbortPolicy
            CallerRunsPolicy
            DiscardPolicy 
            DiscardOldestPolicy
     */
    private static void RejectedExecutionHandlerDemo(List<Runnable> tasks) {
        // defaultPolicy(tasks);

        // discardPolicy(tasks);

        discardOldestPolicy(tasks);

        // callerRunsPolicy(tasks);
    }

    /*
        在调用execute()的线程中运行任务
     */
    private static void callerRunsPolicy(List<Runnable> tasks) {
        ThreadPoolExecutor callerRunsPolicyPool = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(4), new ThreadPoolExecutor.CallerRunsPolicy());

        tasks.forEach(callerRunsPolicyPool :: execute);

        callerRunsPolicyPool.shutdown();

        //运行结果：
        // main: tasttask7 start running
        // pool-1-thread-1: tasttask1 start running
        // pool-1-thread-2: tasttask6 start running
        // main: tasttask7 end!
        // main: tasttask8 start running
        // pool-1-thread-2: tasttask6 end!
        // pool-1-thread-1: tasttask1 end!
        // pool-1-thread-2: tasttask2 start running
        // pool-1-thread-1: tasttask3 start running
        // pool-1-thread-1: tasttask3 end!
        // main: tasttask8 end!
        // pool-1-thread-2: tasttask2 end!
        // pool-1-thread-2: tasttask5 start running
        // pool-1-thread-1: tasttask4 start running
        // pool-1-thread-1: tasttask4 end!
        // pool-1-thread-1: tasttask9 start running
        // pool-1-thread-2: tasttask5 end!
        // pool-1-thread-2: tasttask10 start running
        // pool-1-thread-2: tasttask10 end!
        // pool-1-thread-1: tasttask9 end!
    }

    /*
        抛弃队列头的任务，把当前任务加到队列尾 (可看源码实现)
     */
    private static void discardOldestPolicy(List<Runnable> tasks) {
        ThreadPoolExecutor discardOldestPolicyPool = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), new ThreadPoolExecutor.DiscardOldestPolicy());

        // 打断点观察 discardPolicyPool各个属性值的变化
        tasks.forEach(t -> {
            discardOldestPolicyPool.execute(t);

            // 打印队列
            List<String> taskNames = discardOldestPolicyPool.getQueue()
                    .stream()
                    .map(r -> (( NameTask ) r).getName())
                    .collect(Collectors.toList());
            System.out.println(taskNames);
        });

        discardOldestPolicyPool.shutdown();

        // 运行结果：
        // pool-1-thread-1: tasttask1 start running
        // []
        // [task2]
        // [task2, task3]
        // [task2, task3, task4]
        // [task2, task3, task4, task5]
        // [task2, task3, task4, task5]
        // [task3, task4, task5, task7]
        // [task4, task5, task7, task8]
        // [task5, task7, task8, task9]
        // [task7, task8, task9, task10]
        // pool-1-thread-2: tasttask6 start running
        // pool-1-thread-1: tasttask1 end!
        // pool-1-thread-1: tasttask7 start running
        // pool-1-thread-2: tasttask6 end!
        // pool-1-thread-2: tasttask8 start running
        // pool-1-thread-1: tasttask7 end!
        // pool-1-thread-1: tasttask9 start running
        // pool-1-thread-2: tasttask8 end!
        // pool-1-thread-2: tasttask10 start running
        // pool-1-thread-1: tasttask9 end!
        // pool-1-thread-2: tasttask10 end!
    }

    /*
        无视，直接舍弃任务(可看源码，rejectedExecution()方法中无任何处理)
     */
    private static void discardPolicy(List<Runnable> tasks) {
        ThreadPoolExecutor discardPolicyPool = new ThreadPoolExecutor(1, 1, 10,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        // 打断点观察 discardPolicyPool各个属性值的变化
        tasks.forEach(t -> {
            discardPolicyPool.execute(t);
        });

        discardPolicyPool.shutdown();

        //运行结果： 其他的任务均被无视了
        // pool-1-thread-1: tasttask1 start running
        // pool-1-thread-1: tasttask1 end!
    }

    /*
       JDK默认的处理策略-AbortPolicy: 直接抛出RejectedExecutionException
     */
    private static void defaultPolicy(List<Runnable> tasks) {
        ThreadPoolExecutor deafult = new ThreadPoolExecutor(1, 1, 10,
                TimeUnit.SECONDS, new SynchronousQueue<>());

        tasks.forEach(deafult :: execute);

        deafult.shutdown();

        // 运行结果：
        // pool-1-thread-1: tasttask1 start running
        // Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task 多线程.NameTask@7ba4f24f rejected from java.util.concurrent.ThreadPoolExecutor@3b9a45b3[Running, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
        //         at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
        //         at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
        //         at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
        //         at java.util.ArrayList.forEach(ArrayList.java:1257)
        //         at 多线程.ThreadPoolExecutorDemo.RejectedExecutionHandlerDemo(ThreadPoolExecutorDemo.java:65)
        //         at 多线程.ThreadPoolExecutorDemo.main(ThreadPoolExecutorDemo.java:50)
        // pool-1-thread-1: tasttask1 end!


    }

    private static void ThreadFactoryDemo() {

        // 默认为 优先级：NORM_PRIORITY 的非守护线程
        ThreadFactory defaultFactory = Executors.defaultThreadFactory();

        // commons-lang3 第三方jar的工具类实现 不推荐自己实现
        ThreadFactory customizeFactory = new BasicThreadFactory.Builder()
                .daemon(true)//设置是否为守护线程
                .namingPattern("Test-Task-%d")// 正则表达式规定线程名
                .priority(NORM_PRIORITY)//设置线程优先级
                .build();

    }

    private static void workQueueDemo(List<Runnable> tasks) {

        // SynchronousQueueDemo(tasks);

        // ArrayBlockingQueueDemo(tasks);

        LinkedBlockingQueueDemo(tasks);
    }

    /*
        无界队列 maximumPoolSize参数无效，理论上可以缓冲无限的任务。同时刻可执行最大任务数 = corePoolSize；最多也只创建 corePoolSize个线程
     */
    private static void LinkedBlockingQueueDemo(List<Runnable> tasks) {
        ThreadPoolExecutor linkBlockingPool = new ThreadPoolExecutor(1,
                3,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new RejectedHander());
        tasks.forEach(linkBlockingPool :: execute);

        linkBlockingPool.shutdown();

        // 运行结果：
        // pool-1-thread-1: tasttask1 start running
        // pool-1-thread-1: tasttask1 end!
        // pool-1-thread-1: tasttask2 start running
        // pool-1-thread-1: tasttask2 end!
        // pool-1-thread-1: tasttask3 start running
        // pool-1-thread-1: tasttask3 end!
        // pool-1-thread-1: tasttask4 start running
        // pool-1-thread-1: tasttask4 end!
        // pool-1-thread-1: tasttask5 start running
        // pool-1-thread-1: tasttask5 end!
        // pool-1-thread-1: tasttask6 start running
        // pool-1-thread-1: tasttask6 end!
        // pool-1-thread-1: tasttask7 start running
        // pool-1-thread-1: tasttask7 end!
        // pool-1-thread-1: tasttask8 start running
        // pool-1-thread-1: tasttask8 end!
        // pool-1-thread-1: tasttask9 start running
        // pool-1-thread-1: tasttask9 end!
        // pool-1-thread-1: tasttask10 start running
        // pool-1-thread-1: tasttask10 end!

    }

    /*
        有界队列 最大可缓存数=队列的长度  同时刻最大可执行任务数： maximumPoolSize + ArrayBlockingQueue队列长度
     */
    private static void ArrayBlockingQueueDemo(List<Runnable> tasks) {
        // 核心线程1 最大线程3 队列最大长度3
        ThreadPoolExecutor arrayBlockingPool = new ThreadPoolExecutor(1,
                3,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                new RejectedHander());

        tasks.forEach(arrayBlockingPool :: execute);

        arrayBlockingPool.shutdown();

        // 运行结果:
        // rejected task7
        // rejected task8
        // rejected task9
        // rejected task10
        // pool-1-thread-1: tasttask1 start running
        // pool-1-thread-2: tasttask5 start running
        // pool-1-thread-3: tasttask6 start running
        // pool-1-thread-2: tasttask5 end!
        // pool-1-thread-1: tasttask1 end!
        // pool-1-thread-2: tasttask2 start running
        // pool-1-thread-1: tasttask3 start running
        // pool-1-thread-3: tasttask6 end!
        // pool-1-thread-3: tasttask4 start running
        // pool-1-thread-1: tasttask3 end!
        // pool-1-thread-2: tasttask2 end!
        // pool-1-thread-3: tasttask4 end!
    }

    /*
        SynchronousQueue: 直接提交，实际上并没有缓冲作用 同时刻最大可执行任务数 maximumPoolSize
     */
    private static void SynchronousQueueDemo(List<Runnable> tasks) {
        // 核心线程 1 最大线程 3
        ThreadPoolExecutor syncPool = new ThreadPoolExecutor(1,
                3,
                10,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new RejectedHander());

        tasks.forEach(syncPool :: execute);

        syncPool.shutdown();

        //运行结果：
        // rejected task4
        // pool-1-thread-1: tasttask1 start running
        // pool-1-thread-3: tasttask3 start running
        // pool-1-thread-2: tasttask2 start running
        // rejected task5
        // rejected task6
        // rejected task7
        // rejected task8
        // rejected task9
        // rejected task10
        // pool-1-thread-1: tasttask1 end!
        // pool-1-thread-3: tasttask3 end!
        // pool-1-thread-2: tasttask2 end!

    }

}

class NameTask implements Runnable {
    private final String name;

    public NameTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": tast" + name + " start running");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": tast" + name + " end!");
    }
}


class RejectedHander implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("rejected " + (( NameTask ) r).getName());
    }
}
