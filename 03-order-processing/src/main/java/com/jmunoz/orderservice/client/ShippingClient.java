package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.shipping.ShippingRequest;
import com.jmunoz.orderservice.model.shipping.ShippingResponse;

public interface ShippingClient {

    ShippingResponse schedule(ShippingRequest request);
}
