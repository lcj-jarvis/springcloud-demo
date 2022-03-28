package com.alibaba.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:13
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RocketMqConsumer9008 {
    public static void main(String[] args) {
        SpringApplication.run(RocketMqConsumer9008.class, args);
    }
}
