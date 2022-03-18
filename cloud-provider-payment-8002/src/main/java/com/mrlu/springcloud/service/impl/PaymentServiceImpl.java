package com.mrlu.springcloud.service.impl;

import com.mrlu.springcloud.dao.PaymentDao;
import com.mrlu.springcloud.entitis.Payment;
import com.mrlu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 简单de快乐
 * @date 2021-10-08 22:36
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
