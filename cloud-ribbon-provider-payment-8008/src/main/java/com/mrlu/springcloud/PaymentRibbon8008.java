package com.mrlu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 简单de快乐
 * @date 2021-10-11 21:55
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentRibbon8008 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentRibbon8008.class, args);
    }
}
