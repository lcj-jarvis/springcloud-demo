package com.mrlu.springcloud.service;

/**
 * @author 简单de快乐
 * @date 2021-10-13 21:56
 */
public interface PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    String paymentInfo_OK(Integer id);

    /**
     * 超时访问
     * @param id
     * @return
     */
    String paymentInfo_TimeOut(Integer id);

    /**
     * 该接口用于测试服务熔断
     * @param id
     * @return
     */
    String paymentCircuitBreaker(Integer id);
}
