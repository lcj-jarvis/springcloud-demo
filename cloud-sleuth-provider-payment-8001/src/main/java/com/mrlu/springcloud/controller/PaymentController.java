package com.mrlu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 简单de快乐
 * @date 2021-10-29 22:30
 */
@RestController
public class PaymentController {

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am payment zipkin server fall back，O(∩_∩)O哈哈~";
    }
}
