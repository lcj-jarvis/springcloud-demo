package com.alibaba.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 简单de快乐
 * @date 2021-11-01 23:20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderNacosMain81
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderNacosMain81.class, args);
    }
}
