package com.jmunoz.orderservice.model.shipping;

import com.jmunoz.orderservice.model.common.Address;

public record Recipient(String name,
                        Address address) {
}
