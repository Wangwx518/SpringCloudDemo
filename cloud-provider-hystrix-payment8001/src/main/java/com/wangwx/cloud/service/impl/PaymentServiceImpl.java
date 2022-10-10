package com.wangwx.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wangwx.cloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName paymentServiceImpl
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/9 14:51
 * @Version 1.0
 **/
@Service
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentServiceImpl implements PaymentService {
    /*
     * 正常访问的方法
     * @author wangwx9
     * @param id
     * @return String
     * @date 2022/10/914:53
     *
     */
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_Ok,id:  " + id + "\t";
    }


    //超时方法
    @Override
    //@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
    //        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    //})
    @HystrixCommand
    public String paymentInfo_TimeOut(Integer id) {
        //int timeOutNumber = 3;
        int age = 10 / 0;

        //try {
        //    TimeUnit.SECONDS.sleep(timeOutNumber);
        //}catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //return "线程池："+Thread.currentThread().getName() + "   paymentInfo_TimeOut,id:   "+id+"\t"+"耗时"+timeOutNumber+"s";
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_TimeOut";

    }

    //无论上述方法超时或是异常报错，皆会走此方法！
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "   系统繁忙或者运行报错，请稍后再试！";

    }

    //下面是全局fallback
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ToT)/~";
    }


    //***********************************服务熔断*******************************
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("****id不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }


    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能负数，请稍后再试，/(ToT)/~id:" + id;

    }
}
