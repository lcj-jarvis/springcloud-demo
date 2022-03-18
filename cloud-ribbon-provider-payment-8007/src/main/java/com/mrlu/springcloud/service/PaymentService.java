package com.mrlu.springcloud.service;


import com.mrlu.springcloud.entitis.Payment;

/**
 * @author 简单de快乐
 * @date 2021-10-08 22:36
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
