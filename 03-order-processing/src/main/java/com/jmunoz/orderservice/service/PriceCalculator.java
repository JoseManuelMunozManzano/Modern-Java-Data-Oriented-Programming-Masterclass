package com.jmunoz.orderservice.service;

import com.jmunoz.orderservice.model.common.PriceSummary;
import com.jmunoz.orderservice.model.order.Order;

public interface PriceCalculator {

    PriceSummary calculate(Order order);
}
