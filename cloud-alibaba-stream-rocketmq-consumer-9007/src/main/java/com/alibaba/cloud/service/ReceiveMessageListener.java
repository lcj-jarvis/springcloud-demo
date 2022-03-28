package com.alibaba.cloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import static com.alibaba.cloud.service.MySink.INPUT1;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:24
 */
@Service
@EnableBinding(MySink.class)
public class ReceiveMessageListener {

    /**
     * @StreamListener的values属性：方法订阅的绑定目标（例如通道）的名称
     * 和application.yml文件的input1一致
     * @param receiveMsg
     */
    @StreamListener(INPUT1)
    public void receiveInput1(String receiveMsg) {
        System.out.println("input1 receive: " + receiveMsg);
    }

}
