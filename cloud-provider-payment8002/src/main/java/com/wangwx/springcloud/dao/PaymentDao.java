package com.wangwx.springcloud.dao;

import com.wangwx.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PaymentDao
 * @Description TODO
 * @Author wangwx9
 * @Date 2022/10/3 11:23
 * @Version 1.0
 **/
@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(@Param("paymentId") Long id);
}
