package com.jmunoz.orderservice.model.payment;

import java.util.UUID;

public sealed interface PaymentStatus {

    // Esto es opcional.
    // Estos campos son obligatorios para cualquier implementación de PaymentStatus.
    UUID orderId();
    double amount();

    record Processed(String transactionId,
                     UUID orderId,
                     double amount) implements PaymentStatus {
    }

    // Cuando se rechaza, solo obtenemos el status 402, pero nosotros vamos a construir
    // este record.
    record Declined(UUID orderId,
                    double amount) implements PaymentStatus {
    }
}
