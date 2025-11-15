package com.jmunoz.orderservice.service;

import com.jmunoz.orderservice.model.order.Order;
import com.jmunoz.orderservice.model.shipping.ShippingResponse;
import com.jmunoz.orderservice.model.shipping.ShippingStatus;

public interface ShippingService {

    // Queda como histórico de que para la fase 1 devolvíamos ShippingResponse
//    ShippingResponse scheduleShipping(Order order);

    // Para la fase 3 devolvemos ShippingStatus.
    ShippingStatus scheduleShipping(Order order);
}
