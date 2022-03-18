package com.mrlu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author 简单de快乐
 * @date 2021-10-12 0:28
 */
public interface LoadBalancer {

    /**
     * 返回负载均衡后的ServiceInstance
     * @param instances
     * @return
     */
    ServiceInstance getInstance(List<ServiceInstance> instances);
}
