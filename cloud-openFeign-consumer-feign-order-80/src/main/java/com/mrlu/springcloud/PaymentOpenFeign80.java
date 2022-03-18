package com.mrlu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 简单de快乐
 * @date 2021-10-12 21:37
 *
 * @EnableFeignClients 注解表示开启openFeign
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PaymentOpenFeign80 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentOpenFeign80.class, args);
    }
}
