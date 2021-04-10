package JDK8.日期;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDate d = LocalDate.now(); // 当前日期
        LocalTime t = LocalTime.now(); // 当前时间
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        System.out.println(d); // 严格按照ISO 8601格式打印
        System.out.println(t); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印
        
        d = dt.toLocalDate();
        t = dt.toLocalTime();
        
        // 指定时间 日期创建对象
        LocalDate d2 = LocalDate.of(2019, 11, 30); // 2019-11-30, 注意11=11月
        LocalTime t2 = LocalTime.of(15, 16, 17); // 15:16:17
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);
        LocalDateTime dt3 = LocalDateTime.of(d2, t2);
        
        //标准格式传入 ：ISO 8601规定的日期和时间分隔符是T
        // JDK8.日期：yyyy-MM-dd
        // 时间：HH:mm:ss
        // 带毫秒的时间：HH:mm:ss.SSS
        // 日期和时间：yyyy-MM-dd'T'HH:mm:ss
        // 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
        dt = LocalDateTime.parse("2019-11-19T15:16:17");
        d = LocalDate.parse("2019-11-19");
        t = LocalTime.parse("15:16:17");
        
        // DateTimeFormatter 自定义输出格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
        
        // 用自定义格式解析:
        dt2 = LocalDateTime.parse("2019/11/30 15:16:17", dtf);
        System.out.println(dt2);
        
        // 时间 JDK8.日期 比较 isBefore(); isAfter();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));
        
        // LocalDateTime 无时区信息 所以无法与时间戳做转化
        
        // Duration表示两个时刻之间的时间间隔。
        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration duration = Duration.between(start, end);
        System.out.println(duration); // PT1235H10M30S
        
        // Period表示两个日期之间的天数：
        Period p = LocalDate.of(2019, 11, 19).until(LocalDate.of(2020, 1, 9));
        System.out.println(p); // P1M21D
        
        // 获取5天前的时间
        log.info("Time:{}", LocalDateTime.now().minusDays(5));
        
    }
}
