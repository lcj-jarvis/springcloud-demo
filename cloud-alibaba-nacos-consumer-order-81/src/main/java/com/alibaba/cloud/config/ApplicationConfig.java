package com.alibaba.cloud.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 简单de快乐
 * @date 2021-11-01 23:21
 */
@Configuration
public class ApplicationConfig {

    // @LoadBalanced 该注解支持负载均衡
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Defines the client configuration used by various APIs to initialize clients or load balancers
     * and for method execution. The default implementation is {@link DefaultClientConfigImpl}.
     * 定义各种 API 用于初始化客户端或负载均衡器以及方法执行的客户端配置。 默认实现是DefaultClientConfigImpl
     * @author awang
     *
     */
    @Bean
    public IClientConfig iClientConfig() {
        DefaultClientConfigImpl clientConfig = new DefaultClientConfigImpl();
        // 设置负载均衡的服务名称
        clientConfig.setClientName("cloud-alibaba-nacos-payment-provider");
        return clientConfig;
    }
}
