package com.jmunoz.orderservice.service.impl;

import com.jmunoz.orderservice.client.CustomerClient;
import com.jmunoz.orderservice.client.ProductClient;
import com.jmunoz.orderservice.exception.ApplicationExceptions;
import com.jmunoz.orderservice.model.order.CreateOrderCommand;
import com.jmunoz.orderservice.model.order.Order;
import com.jmunoz.orderservice.model.order.OrderItem;
import com.jmunoz.orderservice.model.product.Product;
import com.jmunoz.orderservice.model.product.ProductStatus.*;
import com.jmunoz.orderservice.service.RequestValidatorService;

import java.time.LocalDate;

// Este servicio depende de ProductClient y CustomerClient.
// Validará que obtenemos la data correcta de esos clientes.
// Generará el objeto Order.
public class RequestValidatorServiceImpl implements RequestValidatorService {

    // Inyectados por el constructor.
    private final ProductClient productClient;
    private final CustomerClient customerClient;

    public RequestValidatorServiceImpl(ProductClient productClient, CustomerClient customerClient) {
        this.productClient = productClient;
        this.customerClient = customerClient;
    }

    @Override
    public Order validate(CreateOrderCommand request) {
        var product = this.getProduct(request.productId());
        var customer = this.customerClient.getCustomer(request.customerId());
        var orderItem = new OrderItem(product, request.quantity());
        return new Order(request.orderId(), customer, orderItem, LocalDate.now());
    }

    // El caso complicado es Discontinued porque no podemos continuar con el flujo de trabajo.
    private Product getProduct(String productId) {
        return switch (this.productClient.getProductStatus(productId)) {
            case Active active -> active.product();
            case Discontinued discontinued -> ApplicationExceptions.discontinuedProduct(discontinued);
        };
    }
}
