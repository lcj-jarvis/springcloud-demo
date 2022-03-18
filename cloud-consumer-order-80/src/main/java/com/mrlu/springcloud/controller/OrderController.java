package com.mrlu.springcloud.controller;

import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 简单de快乐
 * @date 2021-10-09 21:31
 */
@RestController
@Slf4j
public class OrderController {

    // 不能写死
    // public static final String PAYMENT_URL = "http://localhost:8001";

    /**
     * 通过在eureka上注册过的服务名称来调用
     */
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommentResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommentResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
    }

    @GetMapping("/consumer/payment/discovery")
    public CommentResult<DiscoveryClient> discovery() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/discovery", CommentResult.class);
    }
}
