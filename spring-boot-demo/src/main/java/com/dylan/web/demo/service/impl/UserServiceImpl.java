package com.dylan.web.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dylan.web.demo.constants.DBLockConstants;
import com.dylan.web.demo.mapper.LockMapper;
import com.dylan.web.demo.mapper.auto.UserMapper;
import com.dylan.web.demo.model.DBLock;
import com.dylan.web.demo.model.auto.User;
import com.dylan.web.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoulei
 * @since 2021-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LockMapper lockMapper;

    @Override
    @Transactional
    public List<User> findAllUser() {
        // synchronized (DBLockConstants.USER_RESOURCE) {

        String name = Thread.currentThread().getName();
        DBLock lock = lockMapper.selectLockForUpdate(DBLockConstants.USER_RESOURCE);

        if (Objects.isNull(lock)) {
            log.info(name + "获取数据库分布式锁失败");
            return Collections.emptyList();
        }
        log.info(name + "获取数据库分布式锁成功");
        // lockMapper.insert(new DBLock(DBLockConstants.USER_RESOURCE,
        //         Thread.currentThread().getName(),
        //         true));
        List<User> listUser = userMapper.findAllUser();
        log.info(name + "释放数据库分布式锁");
        return listUser;

        // }
    }
}
