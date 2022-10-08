package com.wangwx.springcloud.service;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.wangwx.springcloud.entity.CommonResult;
import spring.wangwx.springcloud.entity.Payment;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/getPaymentById/{paymentId}")
    public CommonResult<Payment> getPaymentById(@PathVariable("paymentId") Long paymentId);
    @GetMapping(value= "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
