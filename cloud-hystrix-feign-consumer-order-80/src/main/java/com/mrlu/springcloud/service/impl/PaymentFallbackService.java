package com.mrlu.springcloud.service.impl;

import com.mrlu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author 简单de快乐
 * @date 2021-10-14 22:34
 *
 * 单独的服务降级方法所在的类
 */
/*@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务端paymentInfo_OK接口调用失败，提示来自：cloud-hystrix-consumer-feign-order-80";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "服务端paymentInfo_TimeOut接口调用失败，提示来自：cloud-hystrix-consumer-feign-order-80";
    }
}*/
