package 日期.JDK8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now();//获取服务器默认时区当前时间
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));

        // 表示的时刻相同
        System.out.println(zbj);
        System.out.println(zny);

        // LoaclDateTime + ZoneId -> ZonedDateTime
        LocalDateTime dt = LocalDateTime.now();
        zbj = dt.atZone(ZoneId.systemDefault());
        zny = dt.atZone(ZoneId.of("America/New_York"));

        // 表示的时刻不同
        System.out.println(zbj);
        System.out.println(zny);

        // withZoneSameInstant()将关联时区转换到另一个时区
        zbj = ZonedDateTime.now(ZoneId.systemDefault());
        zny = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zbj);
        System.out.println(zny);

        // 转化为本地时间
        LocalDateTime ldt = zny.toLocalDateTime();//转换为LocalDateTime时，直接丢弃了时区信息,并不是转化为当前服务器设置的默认时区的本地时间
        System.out.println(ldt);


    }
}
