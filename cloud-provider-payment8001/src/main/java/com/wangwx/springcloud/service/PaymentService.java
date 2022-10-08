package com.wangwx.springcloud.service;

import com.wangwx.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("paymentId") Long id);
}
