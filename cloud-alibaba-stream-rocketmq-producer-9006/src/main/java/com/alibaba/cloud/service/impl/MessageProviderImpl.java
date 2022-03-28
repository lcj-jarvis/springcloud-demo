package com.alibaba.cloud.service.impl;

import com.alibaba.cloud.service.MessageProvider;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:16
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    /**
     * 消息发送的管道
     */
    @Autowired
    public MessageChannel output;

    public static final String TAG_STR = "tagStr";
    public static final String TAG_STR_TEMP = "tagStrTemp";

    @Override
    public String send() {
        int nextInt = ThreadLocalRandom.current().nextInt();
        String tag = nextInt % 2 == 0 ? TAG_STR : TAG_STR_TEMP;
        System.out.println("tag:" + tag);
        String msg = UUID.randomUUID().toString();
        // 设置消息的tag
        Message<String> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag).build();
        output.send(message);
        System.out.println("msg:" + msg);
        return msg;
    }
}
