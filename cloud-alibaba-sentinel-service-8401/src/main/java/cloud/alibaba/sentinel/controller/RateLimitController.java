package cloud.alibaba.sentinel.controller;

import cloud.alibaba.sentinel.handler.CustomerBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 简单de快乐
 * @date 2021-11-12 21:31
 */
@RestController
public class RateLimitController {

    /**
     * 1、演示按资源名称限流+后续处理
     *
     * 根据@SentinelResource注解的value设置的资源名称进行限流，
     * 并设置blockHandler属性的值，指定限流规则的兜底方法
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "myResource", blockHandler = "handleException")
    public CommentResult byResource() {
        return new CommentResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommentResult handleException(BlockException exception) {
        return new CommentResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    /**
     * 2、演示按照Url地址限流+后续处理
     *
     * 没有给@SentinelResource的blockerHandler设置值，也就是没有指定限流规则的兜底方法
     * 采用系统自带的限流Blocked by Sentinel (flow limiting)
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommentResult byUrl() {
        return new CommentResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }


    /**
     * @SentinelResource的blockHandlerClass属性 指定用户自定义限流的处理逻辑所在的类
     * blockHandler： 指定用户自定义限流的处理逻辑所在的方法（该方法必须是静态的）
     *
     * 同理
     * fallback：指定程序自身出现异常而触发限流处理逻辑所在的方法（并不是违背限流规则导致的限流）
     * fallbackClass：指定程序自身出现异常而触发限流处理逻辑所在的方法的类名
     *
     * 以下配置表示，如果触发了配置限流规则，就走CustomerBlockHandler类里的handleException方法处理
     *
     * https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81
     * 官网说明：
     * blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，
     * 参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
     * blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass
     * 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     */
    @GetMapping("/rateLimit/customBlockHandler")
    @SentinelResource(value = "customBlockHandler", blockHandler = "customHandleException",
            blockHandlerClass = CustomerBlockHandler.class)
    public CommentResult customBlockHandler() {
        return new CommentResult(200,"按用户自定义限流处理逻辑");
    }

    // ===========================持久化限流规则配置==================================
    @GetMapping("/rateLimit/persistence01")
    @SentinelResource(value = "persistence01", blockHandler = "persistenceHandler")
    public CommentResult persistenceTest01() {
        return new CommentResult(200, "persistenceTest01成功", "ok");
    }

    public CommentResult persistenceHandler(BlockException exception) {
        return new CommentResult(444, "触发持久化配置的限流规则");
    }

    @GetMapping("/rateLimit/persistence02")
    @SentinelResource(value = "persistence02", blockHandler = "persistenceHandler")
    public CommentResult persistenceTest02() {
        return new CommentResult(200, "persistenceTest02成功");
    }
}