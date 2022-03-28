package com.alibaba.cloud.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author 简单de快乐
 * @create 2022-03-28 22:11
 */
public interface MySink {

    String INPUT2 = "input2";

    @Input(INPUT2)
    SubscribableChannel input2();
}
