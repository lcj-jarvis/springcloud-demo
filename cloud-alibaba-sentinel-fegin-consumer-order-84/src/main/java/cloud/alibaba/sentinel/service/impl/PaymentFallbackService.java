package cloud.alibaba.sentinel.service.impl;

import cloud.alibaba.sentinel.service.PaymentService;
import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import org.springframework.stereotype.Component;

/**
 * @author 简单de快乐
 * @date 2021-11-13 21:19
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommentResult<Payment> paymentSQL(Long id) {
        return new CommentResult<>(444,"服务降级返回,没有该流水信息",new Payment(id, "errorSerial......"));
    }
}
