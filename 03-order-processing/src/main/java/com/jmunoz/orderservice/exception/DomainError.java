package com.jmunoz.orderservice.exception;

import java.util.List;
import java.util.UUID;

public sealed interface DomainError extends ApplicationError {

    // Lo que puede ser Entity.
    enum Entity {
        CUSTOMER,
        PRODUCT
    }

    // En vez de tener distintos records como ProductNotFound, CustomerNotFound, etc.
    // tendremos este NotFound genérico que se aplica a Entity (ahí sabemos si es Customer, Product...)
    record EntityNotFound(Entity entity,
                          String id) implements DomainError {
    }

    // Si el producto está discontinuado, mandamos una lista de productos recomendados de la misma categoría.
    record ProductDiscontinued(String productId,
                               List<String> recommendedProducts) implements DomainError {
    }

    record PaymentDeclined(UUID orderId,
                           double amount) implements DomainError {
    }

    record ShippingDeclined(UUID orderId) implements DomainError {
    }
}
