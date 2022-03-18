package cloud.alibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 简单de快乐
 * @date 2021-11-13 0:05
 * <p>
 * <p>
 * 测试@SentinelResource注解的fallback和blockHandler属性
 */
@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    public String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    // 只配置fallback
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")
    // 只配置blockHandler
    // @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    // fallback和blockHandler都配置了
    // @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    // 忽略IllegalArgumentException，将该异常抛出，不调用fallback属性指定的方法
    @SentinelResource(value = "fallback", fallback = "handlerFallback",
            blockHandler = "blockHandler", exceptionsToIgnore = IllegalArgumentException.class)
    public CommentResult<Payment> fallback(@PathVariable Long id) {
        CommentResult<Payment> result = restTemplate.getForObject(serviceUrl + "/paymentSQL/" + id, CommentResult.class, id);

        // 服务提供者模拟的数据没有4
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    // fallback的兜底方法（可以理解为熔断调用）
    public CommentResult handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommentResult<>(444, "兜底异常handlerFallback,exception内容  " + e.getMessage(), payment);
    }

    // blockHandler的兜底方法（可以理解为熔断调用）
    public CommentResult blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommentResult<>(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);
    }

}
