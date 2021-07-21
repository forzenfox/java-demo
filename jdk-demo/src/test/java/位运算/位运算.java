package 位运算;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p></p>
 *
 * @author 周磊
 * @version 1.0
 * @date 2021-05-19
 */
@Slf4j
public class 位运算 {
    
    @Test
    public void 按位与测试() {
        log.info("0&0={},0&1={},1&0={},1&1={}",
                0 & 0, 0 & 1, 1 & 0, 1 & 1);
        
        int a = 1829233;
        log.info("a&0={}", a & 0);
    }
}
