package com.jmunoz.orderservice.model.order;

import com.jmunoz.orderservice.model.customer.Customer;

import java.time.LocalDate;
import java.util.UUID;

public record Order(UUID orderId,
                    Customer customer,
                    OrderItem orderItem,   // Un futuro requerimiento podría hacer que esto fuese una Lista. Por eso se creó la clase OrderItem.
                    LocalDate createdAt) {
}
