package cloud.alibaba.sentinel.contoller;

import cloud.alibaba.sentinel.service.PaymentService;
import com.mrlu.springcloud.entitis.CommentResult;
import com.mrlu.springcloud.entitis.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 简单de快乐
 * @date 2021-11-13 21:13
 */
@RestController
public class CircleBreakerController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommentResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        if(id == 4) {
            throw new RuntimeException("没有该id");
        }
        return paymentService.paymentSQL(id);
    }
}
