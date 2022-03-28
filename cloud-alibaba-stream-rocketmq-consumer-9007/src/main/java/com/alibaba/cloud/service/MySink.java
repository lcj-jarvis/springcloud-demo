package com.alibaba.cloud.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author 简单de快乐
 * @create 2022-03-28 22:14
 */
public interface MySink {

    String INPUT1 = "input1";

    @Input(INPUT1)
    SubscribableChannel input1();
}
