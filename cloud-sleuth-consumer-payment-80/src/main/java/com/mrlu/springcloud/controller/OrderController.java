package com.mrlu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 简单de快乐
 * @date 2021-10-29 22:41
 */
@RestController
public class OrderController {

    @Autowired
    public RestTemplate restTemplate;

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        // 测试链路追踪
        return restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
    }
}
