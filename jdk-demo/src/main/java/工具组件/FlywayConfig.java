package 工具组件;

import org.flywaydb.core.Flyway;

/**
 * <p></p>
 *
 * @author 周磊
 * @version 1.0
 * @date 2021-04-25
 */
public class FlywayConfig {
    public static Flyway config() {
        return Flyway.configure()
                .dataSource("jdbc:mysql://forzenfox.myqnapcloud.com:1033/flyway_test", "root", "123456") // Mysql 8.0
                //.dataSource("jdbc:mysql://forzenfox.myqnapcloud.com:1034/flyway_test", "root", "123456") // Mysql 5.6
                .load();
    }
}
