package com.mrlu.springcloud.lb.impl;

import com.mrlu.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 简单de快乐
 * @date 2021-10-12 0:31
 */
@Component
public class CustomLoadBalancer implements LoadBalancer {

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> instances) {
        if (instances == null || instances.isEmpty()) {
            System.out.println("没有服务....");
            return null;
        }

        return instances.get(getIndexOfInstance(instances));
    }

    private Integer getIndexOfInstance(List<ServiceInstance> instances) {
        while (true) {
            int current = counter.get();
            int next = current == Integer.MAX_VALUE ? 0 : current + 1;
            if (counter.compareAndSet(current, next)) {
                return next % instances.size();
            }
        }
    }
}
