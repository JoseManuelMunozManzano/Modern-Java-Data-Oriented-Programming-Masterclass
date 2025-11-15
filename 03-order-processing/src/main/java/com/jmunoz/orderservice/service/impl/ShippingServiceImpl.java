package com.jmunoz.orderservice.service.impl;

import com.jmunoz.orderservice.client.ShippingClient;
import com.jmunoz.orderservice.model.order.Order;
import com.jmunoz.orderservice.model.product.Product.*;
import com.jmunoz.orderservice.model.shipping.*;
import com.jmunoz.orderservice.service.ShippingService;

import java.util.List;

public class ShippingServiceImpl implements ShippingService {

    // Inyectados por el constructor.
    private final ShippingClient shippingClient;

    public ShippingServiceImpl(ShippingClient shippingClient) {
        this.shippingClient = shippingClient;
    }

    // NOTA: Queda como histórico de la fase 1
//    @Override
//    public ShippingResponse scheduleShipping(Order order) {
//        var request = this.toShippingRequest(order);
//        return this.shippingClient.schedule(request);
//    }

    // Ahora para la fase 3 se devuelve ShippingStatus.
    @Override
    public ShippingStatus scheduleShipping(Order order) {
        var request = this.toShippingRequest(order);
        return this.shippingClient.schedule(request);
    }

    private ShippingRequest toShippingRequest(Order order) {
        var recipient = new Recipient(order.customer().name(), order.customer().address());
        var quantity = order.orderItem().quantity();
        var items = switch (order.orderItem().product()) {
            case Single single -> List.of(new ShipmentItem(single.productId(), quantity));
            case Bundle bundle -> bundle.items()
                    .stream()
                    .map(Single::productId)
                    .map((String id) -> new ShipmentItem(id, quantity))
                    .toList();
        };

        return new ShippingRequest(order.orderId(), recipient, items);
    }
}
