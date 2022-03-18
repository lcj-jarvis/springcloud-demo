package com.mrlu.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author 简单de快乐
 * @date 2021-10-28 22:20
 */
@Service
@EnableBinding(Sink.class)
public class ReceiveMessageListener {

    @Value("${server.port}")
    public String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println(serverPort + "消费者接收到消息：" + message.getPayload());
    }
}
