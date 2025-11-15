package com.jmunoz.orderservice.model.payment;

import java.util.UUID;

public record RefundRequest(String customerId,
                            UUID orderId,
                            double amount,
                            String transactionId) {
}
