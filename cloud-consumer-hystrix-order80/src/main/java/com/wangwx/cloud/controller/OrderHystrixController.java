package com.wangwx.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wangwx.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName consumerOrderController
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/9 15:29
 * @Version 1.0
 **/
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_Ok(id);
    }
    //@HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod",commandProperties = {
    //        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    //})
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    public String paymentTimeOutFallBackMethod(Integer id) {
        return"我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己，o(T_T)o";
    }

    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，/(ToT)/~";

    }
}
