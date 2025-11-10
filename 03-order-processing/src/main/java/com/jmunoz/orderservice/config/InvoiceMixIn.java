package com.jmunoz.orderservice.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jmunoz.orderservice.model.invoice.Invoice;
import org.springframework.boot.jackson.JsonMixin;

// Spring crea automáticamente el ObjectMapper gracias a la anotación de Spring @JsonMixin
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        defaultImpl = Invoice.Paid.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(Invoice.Paid.class),
        @JsonSubTypes.Type(Invoice.Unpaid.class),
})
@JsonMixin(Invoice.class)
public class InvoiceMixIn {
}
