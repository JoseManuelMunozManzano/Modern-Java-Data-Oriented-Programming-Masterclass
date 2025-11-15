package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.shipping.ShippingRequest;
import com.jmunoz.orderservice.model.shipping.ShippingResponse;
import com.jmunoz.orderservice.model.shipping.ShippingStatus;

public interface ShippingClient {

    // Queda como histórico de que para la fase 1 devolvíamos ShippingResponse
    // ShippingResponse schedule(ShippingRequest request);

    // Para la fase 3 devolvemos ShippingStatus.
    ShippingStatus schedule(ShippingRequest request);
}
