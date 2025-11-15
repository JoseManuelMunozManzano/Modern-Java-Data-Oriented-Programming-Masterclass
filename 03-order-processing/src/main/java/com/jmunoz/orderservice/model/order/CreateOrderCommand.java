package com.jmunoz.orderservice.model.order;

import java.util.Optional;
import java.util.UUID;

public record CreateOrderCommand(UUID orderId,
                                 String customerId,
                                 String productId,
                                 int quantity,
                                 Optional<String> couponCode) {

    // Cuando creamos la instancia, generamos el orderId, de ahí este méto-do de factoría estático.
    // Aquí no necesitamos validaciones porque ya se validó el formato de la petición en el DTO OrderRequest.
    public static CreateOrderCommand create(String customerId, String productId, int quantity, String couponCode) {
        return new CreateOrderCommand(
                UUID.randomUUID(),
                customerId,
                productId,
                quantity,
                Optional.ofNullable(couponCode)
        );
    }
}
