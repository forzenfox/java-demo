package com.dylan.web.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dylan.web.demo.model.DBLock;

public interface LockMapper extends BaseMapper<DBLock> {

    DBLock selectLockForUpdate(String userResource);

}
