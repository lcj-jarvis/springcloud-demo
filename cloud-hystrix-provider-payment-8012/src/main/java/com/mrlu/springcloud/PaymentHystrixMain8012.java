package com.mrlu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 简单de快乐
 * @date 2021-10-13 21:54
 *
 * @EnableCircuitBreaker 注解表示开启服务降级
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixMain8012 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8012.class, args);
    }

}
