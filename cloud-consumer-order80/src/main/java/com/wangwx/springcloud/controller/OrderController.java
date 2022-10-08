package com.wangwx.springcloud.controller;


import com.wangwx.springcloud.entity.CommonResult;
import com.wangwx.springcloud.entity.Payment;
import com.wangwx.springcloud.lb.Impl.MyLb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/3 15:10
 * @Version 1.0
 **/
@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Autowired
    private MyLb myLoadBlancer;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/create2")
    public CommonResult<Payment> create2(Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult(444, "操作失败 ！");
    }

    @GetMapping("/consumer/payment/getPaymentById/{paymentId}")
    public CommonResult<Payment> getPayment(@PathVariable("paymentId") Long paymentId) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + paymentId, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById2/{paymentId}")
    public CommonResult<Payment> getPayment2(@PathVariable("paymentId") Long paymentId) {
        ResponseEntity<CommonResult> resultResponseEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById/" + paymentId, CommonResult.class);
        if (resultResponseEntity.getStatusCode().is2xxSuccessful()) {
            return resultResponseEntity.getBody();
        }
        return new CommonResult(444, "操作失败！");
    }

    @GetMapping(value= "/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances.size() <=0 || instances == null){
            return  null;
        }
        ServiceInstance serviceInstance = myLoadBlancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
}
