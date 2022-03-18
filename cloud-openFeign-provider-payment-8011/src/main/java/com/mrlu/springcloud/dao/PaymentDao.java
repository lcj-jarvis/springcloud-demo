package com.mrlu.springcloud.dao;

import com.mrlu.springcloud.entitis.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 简单de快乐
 * @date 2021-10-08 22:18
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
