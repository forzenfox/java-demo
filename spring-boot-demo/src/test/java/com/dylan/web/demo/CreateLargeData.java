package com.dylan.web.demo;

import com.dylan.web.demo.model.auto.User;
import com.dylan.web.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author 周磊
 * @version 1.0
 * @date 2021-04-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CreateLargeData {
    @Autowired
    private IUserService userService;
    
    @Test
    @Commit
    public void createData() {
        int count = 10000;
        List<User> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = User.builder()
                    .name("小红" + i)
                    .age(18)
                    .build();
            list.add(user);
        }
        
        userService.saveBatch(list);
    }
}
