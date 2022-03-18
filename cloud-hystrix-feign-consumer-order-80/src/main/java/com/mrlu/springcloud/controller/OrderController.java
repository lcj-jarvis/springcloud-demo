package com.mrlu.springcloud.controller;

import com.mrlu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 简单de快乐
 * @date 2021-10-13 22:18
 *
 * @DefaultProperties 注解的defaultFallback属性指定全局回调方法,且默认的全局回调方法不可以有形参
 */
@RestController
@Slf4j
// @DefaultProperties(defaultFallback = "paymentInfoTimeOut_DefaultFallbackMethod")
public class OrderController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }


    /*@HystrixCommand(fallbackMethod = "paymentInfoTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        // 搜索抛出异常，触发服务降级
        // int i = 10 / 0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    /**
     * 服务降级调用的方法
     * @param id
     * @return
     */
    public String paymentInfoTimeOutFallbackMethod(Integer id) {
        System.out.println("服务消费者服务降级：" + id);
        return "特定方法的回调方法;调用8012服务接口超时，或者自己程序错误;当前线程：" + Thread.currentThread().getName();
    }

    /**
     * @HystrixCommand 没有指定特定的回调方法，就使用全局的回调方法
     */
    // @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout01/{id}")
    public String paymentInfo_TimeOut01(@PathVariable("id") Integer id) {
        // 搜索抛出异常，触发服务降级
        // int i = 10 / 0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    /**
     * 全局回调
     * @return
     */
    public String paymentInfoTimeOut_DefaultFallbackMethod() {
        return "全局回调方法;调用8012服务接口超时，或者自己程序错误;当前线程：" + Thread.currentThread().getName();
    }

    // 以下是测试服务熔断

    /**
     * 在服务消费者端测试服务熔断
     *
     * 涉及断路器的三个重要参数：快照时间窗，请求总数阈值，错误百分比阈值
     * 1、快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是
     *             快照时间窗，默认为最近的10s
     * 2、请求总数阈值：在快照时间窗内，必须满足请求总数阈值才有资格熔断。默认为20，意味着在10s内，
     *             如果该Hystrix命令的调用次数不足20次，即使所有的请求都超时或其他原因失败，断路器都不会打开
     * 3、错误百分比阈值：当请求总数在快照时间窗内超过了阈值，比如发送了30次调用，如果在这30次调用中，有15次发生了超时
     *            异常，也就是超过50%的错误比例，在默认设定50%阈值情况下，这时候就会将断路器打开
     * 【更多的配置见笔记】
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 请求次数超过了峰值，熔断器会从关闭状态切换到开启状态
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            // 时间范围
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            // 失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),

    })
    @GetMapping("/consumer/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentCircuitBreaker(id);
    }

    /**
     * 服务熔断的回调方法
     */
    public String paymentCircuitBreaker_fallback(Integer id) {
        return "服务消费者进行服务熔断后调用的方法;id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
