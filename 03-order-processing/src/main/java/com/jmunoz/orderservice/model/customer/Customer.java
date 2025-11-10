package com.jmunoz.orderservice.model.customer;

import com.jmunoz.orderservice.model.common.Address;

public sealed interface Customer {

    // Esto es opcional.
    // Estos campos son obligatorios para cualquier implementación de Customer.
    String id();
    String name();
    Address address();

    record Regular(String id,
                   String name,
                   Address address) implements Customer {
    }

    record Business(String id,
                    String name,
                    String taxId,
                    Address address) implements Customer {
    }
}
