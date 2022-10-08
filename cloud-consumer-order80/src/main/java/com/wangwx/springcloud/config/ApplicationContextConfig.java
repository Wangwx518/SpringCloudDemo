package com.wangwx.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ApplicationContextConfig
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/3 15:12
 * @Version 1.0
 **/
@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced  //使用@LoadBalanced注解赋好RestTemplate负载均衡的能力
    public  RestTemplate geRestTemplate(){
        return new RestTemplate();
    }

}
