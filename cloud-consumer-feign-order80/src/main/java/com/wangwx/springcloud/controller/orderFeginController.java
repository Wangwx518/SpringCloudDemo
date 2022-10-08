package com.wangwx.springcloud.controller;

import com.wangwx.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.wangwx.springcloud.entity.CommonResult;
import spring.wangwx.springcloud.entity.Payment;

/**
 * @ClassName orderFeginController
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/8 17:13
 * @Version 1.0
 **/
@RestController
@Slf4j
public class orderFeginController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/consumer/payment/getPaymentById/{paymentId}")
    public CommonResult<Payment> getPaymentById(@PathVariable("paymentId") Long paymentId) {
        return paymentFeignService.getPaymentById(paymentId);
    }
    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon,客户端默认1s返回结果，超时则报错
      /*  默认Feign客户端只等待一秒钟，但是服务端处理需要超过1秒钟，导致Feign客户端不想等待了，直接返回报错。
        为了避免这样的情况，有时候我们需要设置Feign客户端的超时控制。*/
        return paymentFeignService.paymentFeignTimeout();
    }
}
