package cloud.alibaba.sentinel.service;

import cloud.alibaba.sentinel.service.impl.PaymentFallbackService;
import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 简单de快乐
 * @date 2021-11-13 21:14
 *
 *
 * @FeignClient 的fallback属性指定降级的类。
 * 降级的类必须实现@FeginClient注解标注的接口，然后实现该接口里的方法，编写自己的降级方法
 *
 * 使用 fallback 方式是无法获取异常信息的，
 * 如果想要获取异常信息，可以使用 fallbackFactory参数
 */
@FeignClient(value = "sentinel-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    CommentResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
