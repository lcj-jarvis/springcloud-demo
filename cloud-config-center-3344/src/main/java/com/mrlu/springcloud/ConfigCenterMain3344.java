package com.mrlu.springcloud;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 简单de快乐
 * @date 2021-10-21 22:09
 * @EnableConfigServer 注解表示开启配置
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
