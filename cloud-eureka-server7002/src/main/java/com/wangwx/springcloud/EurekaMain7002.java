package com.wangwx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaMain7002
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/4 11:17
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
public static void main(String[] args) {
    SpringApplication.run(EurekaMain7002.class, args);
}
}
