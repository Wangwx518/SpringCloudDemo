package com.wangwx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ClassName configServerMain3344
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/14 17:37
 * @Version 1.0
 **/
@SpringBootApplication
@EnableConfigServer
public class configServerMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(configServerMain3344.class,args);
    }
}
