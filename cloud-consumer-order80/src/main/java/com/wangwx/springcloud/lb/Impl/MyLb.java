package com.wangwx.springcloud.lb.Impl;

import com.wangwx.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyLb
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/8 15:22
 * @Version 1.0
 **/
@Component
public class MyLb implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获取当前访问次数
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current+1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("****这是第" + next+"次访问****");
        return next;
    }

    //负载均衡算法：rest接口第几次请求数%服务器集胖总数量=实际调用服务器位置下标，每次服务重启动后est接口计数从1开始。
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int i = getAndIncrement() % serviceInstances.size();//轮询
        return serviceInstances.get(i);
    }
}
