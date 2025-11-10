package com.jmunoz.orderservice.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jmunoz.orderservice.model.product.Product;
import org.springframework.boot.jackson.JsonMixin;

// Spring crea automáticamente el ObjectMapper gracias a la anotación de Spring @JsonMixin
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        defaultImpl = Product.Single.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(Product.Single.class),
        @JsonSubTypes.Type(Product.Bundle.class),
})
@JsonMixin(Product.class)
public class ProductMixIn {
}
