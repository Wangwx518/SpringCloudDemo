package com.wangwx.springcloud;

/**
 * @ClassName OederMain80
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/3 15:05
 * @Version 1.0
 **/

import com.wangwx.myrule.RibbonRules;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = RibbonRules.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
