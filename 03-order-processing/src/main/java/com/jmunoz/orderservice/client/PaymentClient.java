package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.payment.PaymentRequest;
import com.jmunoz.orderservice.model.payment.PaymentStatus;

public interface PaymentClient {

    PaymentStatus process(PaymentRequest request);
}
