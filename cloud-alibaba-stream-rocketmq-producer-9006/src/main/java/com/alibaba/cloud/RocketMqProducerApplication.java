package com.alibaba.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
@author 简单de快乐
@create 2022-03-28 20:50

相关教程：
https://github.com/alibaba/spring-cloud-alibaba/blob/2.2.x/spring-cloud-alibaba-examples/rocketmq-example/readme-zh.md
https://www.jianshu.com/p/7f8fd90564ca
配置文件参考：https://github.com/alibaba/spring-cloud-alibaba/wiki/RocketMQ

遇见的问题：
https://www.cnblogs.com/changxy-codest/p/11942976.html
https://blog.csdn.net/weixin_43829154/article/details/108248959
*/
@SpringBootApplication
@EnableDiscoveryClient
public class RocketMqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqProducerApplication.class, args);
    }
}
