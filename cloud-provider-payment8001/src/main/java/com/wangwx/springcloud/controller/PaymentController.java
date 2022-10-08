package com.wangwx.springcloud.controller;

import com.netflix.appinfo.InstanceInfo;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.wangwx.springcloud.entity.CommonResult;
import com.wangwx.springcloud.entity.Payment;
import com.wangwx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/3 11:39
 * @Version 1.0
 **/
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("----插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort:" + serverPort, result);
        }
        return new CommonResult(999, "插入数据失败", null);
    }

    @GetMapping("/payment/getPaymentById/{paymentId}")
    public CommonResult<Payment> create(@PathVariable("paymentId") Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        log.info("----查找结果：" + payment);

        if (payment == null) {
            return new CommonResult(999, "没有记录", null);
        }
        return new CommonResult(200, "查找成功,serverPort:" + serverPort, payment);

    }

    @GetMapping("/payment/disconvery")
    public Object discovery() {
        List<String> servies = discoveryClient.getServices();
        for (String service : servies) {
            log.info("****serviceName :" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value= "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
