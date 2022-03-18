package cloud.alibaba.sentinel.controller;

import cloud.alibaba.sentinel.handler.CustomerBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 简单de快乐
 * @date 2021-11-14 0:07
 */
@RestController
public class DefaultFallbackController {

    @GetMapping("/test1/{id}")
    //@SentinelResource(value = "test1", defaultFallback = "defaultFallbackHandler")
    @SentinelResource(value = "test1", defaultFallback = "defaultFallbackHandler", fallbackClass = CustomerBlockHandler.class)
    public String test1(@PathVariable("id")Integer id) {
        // 触发通用限流方法defaultFallbackHandler
        int i = 10 / id;
        return "test1";
    }

    @GetMapping("/test2/{id}")
    //@SentinelResource(value = "test2", fallback = "test2Fallback", defaultFallback = "defaultFallbackHandler")
    @SentinelResource(value = "test2", fallback = "test2Fallback", defaultFallback = "defaultFallbackHandler", fallbackClass = CustomerBlockHandler.class)
    public String test2(@PathVariable("id")Integer id) {
        int i = 10 / id;
        return "test2";
    }
    public String test2Fallback(Integer id, Throwable exception) {
        return "test2 fallback;" + exception;
    }

    @GetMapping("/test3/{id}")
    //@SentinelResource(value = "test3", defaultFallback = "defaultFallbackHandler")
    @SentinelResource(value = "test3", defaultFallback = "defaultFallbackHandler", fallbackClass = CustomerBlockHandler.class)
    public String test3(@PathVariable("id") Integer id) {
        // 触发通用限流方法defaultFallbackHandler
        int i = 10 / id;
        return "test3";
    }

    public String defaultFallbackHandler(Throwable exception) {
        return "通用defaultFallback;" + exception;
    }

    @GetMapping("/jmeter/test")
    public String test4() {
        return UUID.randomUUID().toString();
    }
}
