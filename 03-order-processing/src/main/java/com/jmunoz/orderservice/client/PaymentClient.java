package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.payment.PaymentRequest;
import com.jmunoz.orderservice.model.payment.PaymentStatus;
import com.jmunoz.orderservice.model.payment.RefundRequest;

public interface PaymentClient {

    PaymentStatus process(PaymentRequest request);

    void refund(RefundRequest request);
}
