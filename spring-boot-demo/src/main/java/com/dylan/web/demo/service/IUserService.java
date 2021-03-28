package com.dylan.web.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dylan.web.demo.model.auto.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoulei
 * @since 2021-03-28
 */
public interface IUserService extends IService<User> {

    List<User> findAllUser();
}
