package com.mrlu.springcloud.controller;

import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import com.mrlu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author 简单de快乐
 * @date 2021-10-08 22:45
 */
@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

//    @GetMapping("/payment/get/{id}")
    @RequestMapping("/payment/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id")Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询的结果:{}", payment);
        if (Objects.isNull(payment)) {
            return new CommentResult<>(500, "查询失败，不存在该数据,serverPort:" + serverPort, null);
        } else {
            return new CommentResult<>(200, "查询成功,serverPort:" + serverPort, payment);
        }
    }

    @GetMapping("/payment/loadBalance")
    public String loadBalance() {
        return serverPort;
    }
}
