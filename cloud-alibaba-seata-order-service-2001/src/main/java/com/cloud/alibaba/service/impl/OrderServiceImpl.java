package com.cloud.alibaba.service.impl;

import com.cloud.alibaba.dao.OrderDao;
import com.cloud.alibaba.domain.Order;
import com.cloud.alibaba.service.AccountService;
import com.cloud.alibaba.service.OrderService;
import com.cloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 简单de快乐
 * @date 2022-01-05 0:25
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    /**
     * 创建订单 -> 调用库存服务扣减库存 -> 调用账户服务扣减账户余额->修改订单状态
     * 即：下订单->减库存->减余额->改状态
     *
     * @GlobalTransactional 开启全局事务.name属性表示全局事务的名称，rollbackFor表示出现什么异常时，全局事务回滚。
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------->下单开始");
        //1、创建订单
        orderDao.create(order);

        //2、远程调用库存服务扣减库存
        log.info("------->order-service中扣减库存开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------->order-service中扣减库存结束");

        //3、远程调用账户服务扣减余额
        log.info("------->order-service中扣减余额开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------->order-service中扣减余额结束");

        // 4、修改订单状态为已完成
        log.info("------->order-service中修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------->order-service中修改订单状态结束");

        log.info("------->下单结束");
    }
}
