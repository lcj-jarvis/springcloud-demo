package cloud.alibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author 简单de快乐
 * @date 2021-11-08 22:26
 */
@RestController
public class FlowLimitController {

    /**
     * 该接口用于测试QPS流控
     */
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    /**
     * 该接口用于测试并发线程数流控
     */
    @GetMapping("/testB")
    public String testB() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + "------testB";
    }

    /**
     * 测试流控模式之关联
     */
    @GetMapping("/testC")
    public String testC() {
        return "------testC";
    }

    /**
     * 测试流控模式之关联
     */
    @GetMapping("/testD")
    public String testD() {
        return "------testD";
    }


    /**
     * 测试流控效果之预
     */
    @GetMapping("/testE")
    public String testE() {
        return "------testE";
    }

    /**
     * 测试流控效果之排队等待
     */
    @GetMapping("/testF")
    public String testF() {
        return "------testF";
    }

    /**
     * 测试限流策略之慢调用比例
     */
    @GetMapping("/testSlowRequestRatio")
    public String testSlowRequestRatio() {
        int num = ThreadLocalRandom.current().nextInt(2, 4);
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testSlowRequestRatio 响应时间:" + num + "s";
    }

    @GetMapping("/testErrorRatio")
    public String testErrorRatio(@RequestParam("num") Integer num) {
        int i = 10 / num;
        return "testErrorRatio";
    }

    @GetMapping("/testErrorCount")
    @SentinelResource()
    public String testErrorCount(@RequestParam("num") Integer num) {
        int i = 10 / num;
        return "testErrorCount";
    }

    /**
     * 热点key
     * @SentinelResource 的value表示表示资源名，blockHandler配置热点key限流的兜底方法，
     * 因为热点key限流会抛出BlockException，所以配置blockHandler
     *
     * 正常执行的过程发送RuntimeException走fallback属性配置的otherExceptionHandler方法
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "myHotKey", blockHandler = "defaultHandler_HotKey",
            fallback = "otherExceptionHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        // int a = 10/0;
        System.out.println("p1:" + p1);
        System.out.println("p2:" + p2);
        return "testHotKey => p1:" + p1 + " p2:" + p2;
    }

    public String defaultHandler_HotKey(String p1, String p2, BlockException exception) {
        return "defaultHandler_HotKey";
    }

    public String otherExceptionHandler(String p1, String p2) {
        return "otherExceptionHandler => p1:" + p1 + " p2:" + p2;
    }
}
