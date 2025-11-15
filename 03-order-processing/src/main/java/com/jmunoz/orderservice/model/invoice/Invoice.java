package com.jmunoz.orderservice.model.invoice;

import com.jmunoz.orderservice.model.common.PriceSummary;

import java.time.LocalDate;
import java.util.UUID;

public sealed interface Invoice {

    // Esto es opcional.
    // Estos campos son obligatorios para cualquier implementación de Invoice.
    String id();

    // id es el id de factura obtenido en la respuesta.
    record Paid(String id,
                UUID orderId,
                String customerId,
                String transactionId,
                PriceSummary priceSummary) implements Invoice {
    }

    // id es el id de factura obtenido en la respuesta.
    record Unpaid(String id,
                  UUID orderId,
                  String customerId,
                  String businessTaxId,
                  PriceSummary priceSummary,
                  LocalDate paymentDue) implements Invoice {
    }
}
