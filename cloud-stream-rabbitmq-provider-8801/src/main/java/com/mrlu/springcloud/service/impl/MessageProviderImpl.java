package com.mrlu.springcloud.service.impl;

import com.mrlu.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author 简单de快乐
 * @date 2021-10-26 22:32
 */
@EnableBinding(Source.class) // 可以理解为消息发送的管道
public class MessageProviderImpl implements MessageProvider {

    /**
     * 消息发送的管道
     */
    @Autowired
    public MessageChannel output;

    @Override
    public String send() {
        String msg = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(msg).build());
        System.out.println("msg:" + msg);
        return msg;
    }
}
