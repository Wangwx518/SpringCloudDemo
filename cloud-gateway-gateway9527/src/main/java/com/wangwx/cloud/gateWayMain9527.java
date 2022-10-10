package com.wangwx.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName gateWayMain9527
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/10 17:31
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class gateWayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(gateWayMain9527.class, args);
    }
}
