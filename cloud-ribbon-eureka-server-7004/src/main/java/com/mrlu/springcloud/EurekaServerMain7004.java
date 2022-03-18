package com.mrlu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 简单de快乐
 * @date 2021-10-11 21:32
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7004 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7004.class, args);
    }
}
