package com.dylan.web.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_lock")
public class DBLock {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // @TableField(value = "resource")
    private final String resource;
    // @TableField(value = "thread")
    private final String thread;
    @TableField(value = "status")
    private final Boolean lock_flag;

    public DBLock(String resource, String thread, Boolean lock_flag) {
        this.resource = resource;
        this.thread = thread;
        this.lock_flag = lock_flag;
    }
}
