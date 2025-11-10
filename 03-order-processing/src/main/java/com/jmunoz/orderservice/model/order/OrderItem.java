package com.jmunoz.orderservice.model.order;

import com.jmunoz.orderservice.model.product.Product;

public record OrderItem(Product product,
                        int quantity) {
}
