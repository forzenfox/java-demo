package com.dylan.web.demo.service.impl;

import com.dylan.web.demo.model.auto.User;
import com.dylan.web.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private IUserService userService;

    @Test
    public void findAllUser() {
        List<Callable<List<User>>> tasks = new ArrayList<>(2);
        tasks.add(() -> userService.findAllUser());
        tasks.add(() -> userService.findAllUser());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 60,
                TimeUnit.SECONDS, new SynchronousQueue<>());

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            log.error("test error:", e);
        }
    }
}