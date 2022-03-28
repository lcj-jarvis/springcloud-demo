package com.alibaba.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RocketMqConsumer9007 {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqConsumer9007.class, args);
    }
}
