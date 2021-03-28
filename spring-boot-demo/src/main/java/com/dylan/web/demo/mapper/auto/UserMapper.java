package com.dylan.web.demo.mapper.auto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dylan.web.demo.model.auto.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoulei
 * @since 2021-03-28
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> findAllUser();
}
