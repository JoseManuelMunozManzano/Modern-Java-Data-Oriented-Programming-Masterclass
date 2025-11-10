package com.jmunoz.orderservice.service;

import com.jmunoz.orderservice.model.order.CreateOrderCommand;
import com.jmunoz.orderservice.model.order.Order;

public interface RequestValidatorService {

    Order validate(CreateOrderCommand request);
}
