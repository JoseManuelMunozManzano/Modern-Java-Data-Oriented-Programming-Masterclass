package com.jmunoz.orderservice.service;

import com.jmunoz.orderservice.model.order.OrderRequest;
import com.jmunoz.orderservice.model.order.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);
}
