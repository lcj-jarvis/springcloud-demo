package com.mrlu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mrlu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 简单de快乐
 * @date 2021-10-13 21:58
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    /**
     * 当服务不可用，这里也就是服务调用超过3s或者出现异常的情况，就执行服务降级的方法paymentInfo_TimeOutHandler
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 手动模拟异常来触发服务降级
        // int i = 10/0;
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: " + id + "\t" + "O(∩_∩)O，耗费5秒";
    }

    /**
     * 服务降级的方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        System.out.println("服务降级：" + id);
        return "调用支付接口超时，8012系统繁忙或出错，当前线程：" + Thread.currentThread().getName();
    }


    /**
     * 测试服务熔断
     * @param id
     * @return
     */
    @Override
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        // IdUtil来源于我们引入的hutool工具包
        // hutool工具官网：https://www.hutool.cn/
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
}
