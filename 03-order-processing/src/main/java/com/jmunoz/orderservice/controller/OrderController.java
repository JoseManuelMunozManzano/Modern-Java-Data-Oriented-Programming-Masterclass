package com.jmunoz.orderservice.controller;

import com.jmunoz.orderservice.model.order.OrderRequest;
import com.jmunoz.orderservice.model.order.OrderResponse;
import com.jmunoz.orderservice.service.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    // Inyectado por el constructor.
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("orders")
    public OrderResponse placeOrder(@Validated @RequestBody OrderRequest orderRequest) {
        return this.orderService.placeOrder(orderRequest);
    }
}
