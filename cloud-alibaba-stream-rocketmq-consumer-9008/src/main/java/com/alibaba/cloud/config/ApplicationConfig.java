package com.alibaba.cloud.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:37
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public IClientConfig iClientConfig() {
        return new DefaultClientConfigImpl();
    }

}
