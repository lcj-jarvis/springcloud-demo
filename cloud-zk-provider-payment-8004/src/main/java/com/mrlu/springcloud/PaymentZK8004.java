package com.mrlu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 简单de快乐
 * @date 2021-10-10 20:48
 */
@SpringBootApplication
@EnableDiscoveryClient // 该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class PaymentZK8004 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentZK8004.class, args);
    }
}
