package cloud.alibaba.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 简单de快乐
 * @date 2021-11-13 21:11
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class OrderFeginMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeginMain84.class, args);
    }
}
