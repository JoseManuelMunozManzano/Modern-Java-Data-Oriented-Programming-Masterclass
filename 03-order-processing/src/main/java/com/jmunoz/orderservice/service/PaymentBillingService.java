package com.jmunoz.orderservice.service;

import com.jmunoz.orderservice.model.common.PriceSummary;
import com.jmunoz.orderservice.model.invoice.Invoice;
import com.jmunoz.orderservice.model.order.Order;

public interface PaymentBillingService {

    Invoice processPayment(Order order, PriceSummary priceSummary);

    void refundPayment(Invoice invoice);
}
