package com.alibaba.cloud.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:36
 */
@Configuration
public class ClientConfig {

    @Bean
    public IClientConfig iClientConfig() {
        return new DefaultClientConfigImpl();
    }
}