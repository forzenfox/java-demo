package com.dylan.web.demo.entity;

import lombok.Data;

@Data
public class PageQuery<T> {
    private T condition;
    private Integer pageSize = 10;
    private Integer pageNum = 1;
}
