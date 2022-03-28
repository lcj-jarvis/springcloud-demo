package com.alibaba.cloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:20
 */
@Service
@EnableBinding(MySink.class)
public class ReceiveMessageListener {

    /**
     * @StreamListener的values属性：方法订阅的绑定目标（例如通道）的名称
     * 和application.yml文件的input2一致
     * @param receiveMsg
     */
    @StreamListener(MySink.INPUT2)
    public void receiveInput2(String receiveMsg) {
        System.out.println("input2 receive: " + receiveMsg);
    }

}
