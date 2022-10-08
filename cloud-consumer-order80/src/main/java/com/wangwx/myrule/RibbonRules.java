package com.wangwx.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RibbonRules
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/7 17:54
 * @Version 1.0
 **/
@Configuration
public class RibbonRules {
    @Bean
    public IRule myRule(){
        return new RandomRule();//随机
    }
}
