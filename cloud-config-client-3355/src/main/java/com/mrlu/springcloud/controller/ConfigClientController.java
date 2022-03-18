package com.mrlu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 简单de快乐
 * @date 2021-10-21 23:59
 *
 * @RefreshScope 注解主要用于动态更新获取配置
 */
@RestController
@RefreshScope
public class ConfigClientController {

    /**
     * 这个配置是在配置中心3344处获取的
     */
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
