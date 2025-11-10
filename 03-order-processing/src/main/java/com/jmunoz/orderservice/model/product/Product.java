package com.jmunoz.orderservice.model.product;

import java.util.List;

public sealed interface Product {

    // Esto es opcional.
    // Estos campos son obligatorios para cualquier implementación de Product.
    String productId();
    String name();

    record Single(String productId,
                  String name,
                  double price) implements Product {
    }

    record Bundle(String productId,
                  String name,
                  double originalPrice,
                  double discountedPrice,
                  List<Single> items) implements Product {
    }
}
