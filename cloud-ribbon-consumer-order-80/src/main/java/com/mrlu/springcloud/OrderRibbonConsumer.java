package com.mrlu.springcloud;

import com.mrlu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author 简单de快乐
 * @date 2021-10-11 22:15
 *
 * 在微服务启动的时候，就会去加载我们自定义好的Ribbon配置类，从而使定义的服务调用策略生效
 * @RibbonClient的name属性 表示服务提供者的服务名称，在注册中心和服务提供者的配置文件都可以看见
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-RIBBON-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderRibbonConsumer {

    public static void main(String[] args) {
        SpringApplication.run(OrderRibbonConsumer.class, args);
    }
}
