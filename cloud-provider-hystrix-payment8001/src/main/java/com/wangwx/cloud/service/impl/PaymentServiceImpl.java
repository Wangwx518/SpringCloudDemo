package com.wangwx.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wangwx.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName paymentServiceImpl
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/9 14:51
 * @Version 1.0
 **/
@Service
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
        return "线程池："+Thread.currentThread().getName() + "   paymentInfo_Ok,id:  "+id+"\t";
    }


    //超时方法
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int timeOutNumber = 5;
        int age = 10/0;

        //try {
        //    TimeUnit.SECONDS.sleep(timeOutNumber);
        //}catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //return "线程池："+Thread.currentThread().getName() + "   paymentInfo_TimeOut,id:   "+id+"\t"+"耗时"+timeOutNumber+"s";
        return "线程池："+Thread.currentThread().getName() + "   paymentInfo_TimeOut";

    }
//无论上述方法超时或是异常报错，皆会走此方法！
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName() + "   系统繁忙或者运行报错，请稍后再试！";

    }
}
