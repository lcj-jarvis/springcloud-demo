package com.mrlu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 简单de快乐
 * @date 2021-10-29 22:39
 */
@SpringBootApplication
@EnableEurekaClient
public class SleuthOrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(SleuthOrderMain80.class, args);
    }
}
