package com.mrlu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 简单de快乐
 * @date 2021-10-10 20:51
 */
@RestController
@Slf4j
public class PaymentZKController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/zk")
    public String paymentZk() {
        return "springcloud with zookeeper:" + serverPort + "-" + UUID.randomUUID().toString();
    }

}
