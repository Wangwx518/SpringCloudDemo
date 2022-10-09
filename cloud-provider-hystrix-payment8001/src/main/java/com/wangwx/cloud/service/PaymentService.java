package com.wangwx.cloud.service;

import org.springframework.stereotype.Service;


public interface PaymentService {
    public String paymentInfo_Ok(Integer id);
    public String paymentInfo_TimeOut(Integer id);

}
