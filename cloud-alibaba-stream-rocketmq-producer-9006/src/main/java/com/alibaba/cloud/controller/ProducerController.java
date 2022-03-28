package com.alibaba.cloud.controller;

import com.alibaba.cloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 简单de快乐
 * @create 2022-03-28 21:14
 */
@RestController
public class ProducerController {

    @Autowired
    private MessageProvider messageProvider;

    @GetMapping("/send")
    public String send() {
        return messageProvider.send();
    }
}
