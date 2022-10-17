package com.wangwx.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigClientController
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/17 14:08
 * @Version 1.0
 **/
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @RequestMapping(value = "/configInfo",method = RequestMethod.GET)
    public String getConfigInfo(){
        return configInfo;
    }
}
