package com.jmunoz.orderservice.service;

import com.jmunoz.orderservice.model.order.Order;
import com.jmunoz.orderservice.model.shipping.ShippingResponse;

public interface ShippingService {

    ShippingResponse scheduleShipping(Order order);
}
