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

    @PostMapping("/payment/create")
    public CommentResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入的结果:{}", result);
        if (result > 0) {
            return new CommentResult<>(200, "插入成功,serverPort:" + serverPort, result);
        } else {
            return new CommentResult<>(500, "插入失败,serverPort:" + serverPort, result);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id")Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询的结果:{}", payment);
        if (Objects.isNull(payment)) {
            return new CommentResult<>(500, "查询失败，不存在该数据,serverPort:" + serverPort, null);
        } else {
            return new CommentResult<>(200, "查询成功,serverPort:" + serverPort, payment);
        }
    }

    /**
     * 服务发现的客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 服务发现，获取服务的详细信息
     * @return
     */
    @GetMapping("/payment/discovery")
    public CommentResult<DiscoveryClient> discovery() {

        // 获取注册中心里所有的服务
        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);

        // 获取指定服务下的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(serviceInstance -> System.out.println("服务名称:" + serviceInstance.getServiceId() + ";服务的ip:" + serviceInstance.getHost() + ";服务的端口号:"
                + serviceInstance.getPort() + ";访问实例的uri:" + serviceInstance.getUri()));

        return new CommentResult<>(200, "成功", this.discoveryClient);
    }
}
