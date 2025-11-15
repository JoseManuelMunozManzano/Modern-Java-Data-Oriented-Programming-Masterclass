package com.jmunoz.orderservice.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jmunoz.orderservice.model.coupon.Coupon;
import org.springframework.boot.jackson.JsonMixin;

// Spring crea automáticamente el ObjectMapper gracias a la anotación de Spring @JsonMixin
@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        defaultImpl = Coupon.None.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(Coupon.None.class),
        @JsonSubTypes.Type(Coupon.Flat.class),
        @JsonSubTypes.Type(Coupon.Percentage.class),
})
@JsonMixin(Coupon.class)
public class CouponMixIn {
}
