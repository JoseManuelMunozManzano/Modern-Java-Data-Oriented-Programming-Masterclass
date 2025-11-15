package com.jmunoz.orderservice.service.impl;

import com.jmunoz.orderservice.client.CouponClient;
import com.jmunoz.orderservice.client.CustomerClient;
import com.jmunoz.orderservice.client.ProductClient;
import com.jmunoz.orderservice.exception.ApplicationExceptions;
import com.jmunoz.orderservice.model.coupon.Coupon;
import com.jmunoz.orderservice.model.order.CreateOrderCommand;
import com.jmunoz.orderservice.model.order.Order;
import com.jmunoz.orderservice.model.order.OrderItem;
import com.jmunoz.orderservice.model.product.Product;
import com.jmunoz.orderservice.model.product.ProductStatus.*;
import com.jmunoz.orderservice.service.RequestValidatorService;

import java.time.LocalDate;

// Este servicio depende de ProductClient, CustomerClient y CouponClient.
// Validará que obtenemos la data correcta de esos clientes.
// Generará el objeto Order.
public class RequestValidatorServiceImpl implements RequestValidatorService {

    // Inyectados por el constructor.
    private final ProductClient productClient;
    private final CustomerClient customerClient;
    private final CouponClient couponClient;

    public RequestValidatorServiceImpl(ProductClient productClient, CustomerClient customerClient, CouponClient couponClient) {
        this.productClient = productClient;
        this.customerClient = customerClient;
        this.couponClient = couponClient;
    }

    @Override
    public Order validate(CreateOrderCommand request) {
        var product = this.getProduct(request.productId());
        var customer = this.customerClient.getCustomer(request.customerId());
        var orderItem = new OrderItem(product, request.quantity());
        // Si el cupón viene (puede no venir) obtenemos su información. Si no viene obtenemos Coupon.NONE.
        var coupon = request.couponCode()
                .map(this.couponClient::getCoupon)
                .orElse(Coupon.none());
        return new Order(request.orderId(), customer, orderItem, coupon, LocalDate.now());
    }

    // El caso complicado es Discontinued porque no podemos continuar con el flujo de trabajo.
    private Product getProduct(String productId) {
        return switch (this.productClient.getProductStatus(productId)) {
            case Active active -> active.product();
            case Discontinued discontinued -> ApplicationExceptions.discontinuedProduct(discontinued);
        };
    }
}
