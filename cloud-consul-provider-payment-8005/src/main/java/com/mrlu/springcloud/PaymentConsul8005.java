package com.mrlu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 简单de快乐
 * @date 2021-10-10 22:27
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsul8005 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentConsul8005.class, args);
    }
}
