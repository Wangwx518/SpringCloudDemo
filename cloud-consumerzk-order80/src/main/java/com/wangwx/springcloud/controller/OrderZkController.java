package com.wangwx.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName OrderZkController
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/4 16:29
 * @Version 1.0
 **/
@RestController
@Slf4j
public class OrderZkController {
    public static final String INVOKE_URL = "http://cloud-providerzk-payment";

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "consumer/payment/zk")
    public String payment(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return  result;
    }


}
