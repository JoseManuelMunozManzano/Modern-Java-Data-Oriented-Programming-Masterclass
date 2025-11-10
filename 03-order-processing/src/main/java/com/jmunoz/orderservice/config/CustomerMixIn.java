package com.jmunoz.orderservice.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jmunoz.orderservice.model.customer.Customer;
import org.springframework.boot.jackson.JsonMixin;

// Spring crea automáticamente el ObjectMapper gracias a la anotación de Spring @JsonMixin
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        defaultImpl = Customer.Regular.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(Customer.Regular.class),
        @JsonSubTypes.Type(Customer.Business.class),
})
@JsonMixin(Customer.class)
public class CustomerMixIn {
}
