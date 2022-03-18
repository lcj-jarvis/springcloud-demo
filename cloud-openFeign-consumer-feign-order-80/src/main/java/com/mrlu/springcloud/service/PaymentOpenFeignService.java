package com.mrlu.springcloud.service;

import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author 简单de快乐
 * @date 2021-10-12 21:41
 * @FeignClient 的value表示提供服务的实例名
 */
@Service
@FeignClient(value = "CLOUD-OPENFEIGN-PAYMENT-SERVICE")
public interface PaymentOpenFeignService {

    /**
     * 直接复制服务提供者的Controller接口过来
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    CommentResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 直接复制服务提供者的Controller接口过来
     * @return
     */
    @GetMapping("/payment/loadBalance")
    String loadBalance();

    /**
     * 测试OpenFeign超时控制
     * @return
     */
    @GetMapping("/payment/timeout")
    String paymentTimeout();
}
