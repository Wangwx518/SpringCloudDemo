package com.wangwx.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName orderHystrixMain80
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/9 15:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableFeignClients
public class orderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(orderHystrixMain80.class, args);
    }
}
