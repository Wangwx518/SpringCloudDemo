package com.wangwx.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "----paymentInfo_Ok------------对方服务器已经宕机，请稍后再试！----";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---- paymentInfo_TimeOut---对方服务器已经宕机，请稍后再试！------------";

    }
}
