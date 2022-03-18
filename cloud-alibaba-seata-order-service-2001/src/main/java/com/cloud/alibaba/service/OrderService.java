package com.cloud.alibaba.service;

import com.cloud.alibaba.domain.Order;

/**
 * @author 简单de快乐
 * @date 2022-01-05 0:24
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
