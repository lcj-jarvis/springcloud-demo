package com.mrlu.springcloud.controller;

import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import com.mrlu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author 简单de快乐
 * @date 2021-10-09 21:31
 */
@RestController
@Slf4j
public class OrderRibbonController {

    /**
     * 通过在eureka上注册过的服务名称来调用
     */
    public static final String PAYMENT_URL = "http://CLOUD-RIBBON-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * RestTemplate的getForObject返回对象为响应体中数据转化成的对象，基本上可以理解为json
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> getPayment01(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
    }

    /**
     * 返回对象为ResponseEntity对象，包含了响应中的一些重要，比如响应头，响应状态码、响应体等
     * 【注】postForObject/postForEntity也是类似的用法
     */
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommentResult<Payment> getPayment02(@PathVariable("id") Long id) {
        ResponseEntity<CommentResult> response = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return new CommentResult<>(444, "操作失败");
        }

    }

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 测试自定义的负载均衡
     * @return
     */
    @GetMapping("/consumer/payment/customLb")
    public String getCustomLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-RIBBON-PAYMENT-SERVICE");
        ServiceInstance loadBalancerInstance = loadBalancer.getInstance(instances);
        System.out.println(loadBalancerInstance.getUri());
        return restTemplate.getForObject(loadBalancerInstance.getUri() + "/payment/loadBalance", String.class);
    }
}