package com.jmunoz.orderservice.model.shipping;

import java.util.List;
import java.util.UUID;

public sealed interface ShippingStatus {

    record Scheduled(UUID orderId,
                     List<Shipment> shipments) implements ShippingStatus {
    }

    // También se podría haber indicado información adicional, pero lo dejamos solo con el id de la orden.
    record Declined(UUID orderId) implements ShippingStatus {
    }
}
