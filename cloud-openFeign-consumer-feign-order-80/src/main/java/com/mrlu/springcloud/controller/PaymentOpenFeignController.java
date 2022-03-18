package com.mrlu.springcloud.controller;

import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import com.mrlu.springcloud.service.PaymentOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 简单de快乐
 * @date 2021-10-12 21:48
 */
@RestController
public class PaymentOpenFeignController {

    @Autowired
    private PaymentOpenFeignService paymentOpenFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentOpenFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/loadBalance")
    public String loadBalance() {
        return paymentOpenFeignService.loadBalance();
    }

    /**
     * 测试OpenFeign超时控制
     * @return
     */
    @GetMapping("/consumer/payment/timeout")
    public String paymentTimeout() {
        return paymentOpenFeignService.paymentTimeout();
    }
}
