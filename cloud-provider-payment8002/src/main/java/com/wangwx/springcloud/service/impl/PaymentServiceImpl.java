package com.wangwx.springcloud.service.impl;

import com.wangwx.springcloud.dao.PaymentDao;
import com.wangwx.springcloud.entity.Payment;
import com.wangwx.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaymentServiceImpl
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/3 11:35
 * @Version 1.0
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
