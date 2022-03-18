package cloud.alibaba.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mrlu.springcloud.entitis.CommentResult;
import org.springframework.stereotype.Component;

/**
 * @author 简单de快乐
 * @date 2021-11-12 22:01
 */
@Component
public class CustomerBlockHandler {

    /**
     * 要求方法为静态的方法
     */
    public static CommentResult customHandleException(BlockException exception){
        return new CommentResult(2020,"自定义的限流处理信息......CustomerBlockHandler");
    }


    public static String test2Fallback(Integer id, Throwable exception) {
        return "CustomerBlockHandler test2 fallback;" + exception;
    }

    public static String defaultFallbackHandler(Throwable exception) {
        return "CustomerBlockHandler 通用defaultFallback;" + exception;
    }
}
