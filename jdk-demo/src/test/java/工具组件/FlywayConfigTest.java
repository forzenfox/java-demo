package 工具组件;

import org.junit.Test;

/**
 * <p></p>
 *
 * @author 周磊
 * @version 1.0
 * @date 2021-04-25
 */
public class FlywayConfigTest {
    
    @Test
    public void migrateTest() {
        FlywayConfig.config().migrate();
    }
    
    @Test
    public void repairTest() {
        FlywayConfig.config().repair();
    }
}