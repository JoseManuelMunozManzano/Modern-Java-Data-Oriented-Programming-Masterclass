package com.jmunoz.orderservice.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jmunoz.orderservice.model.product.ProductStatus;
import org.springframework.boot.jackson.JsonMixin;

// Spring crea automáticamente el ObjectMapper gracias a la anotación de Spring @JsonMixin
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        defaultImpl = ProductStatus.Active.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(ProductStatus.Active.class),
        @JsonSubTypes.Type(ProductStatus.Discontinued.class),
})
@JsonMixin(ProductStatus.class)
public class ProducStatustMixIn {
}
