package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.coupon.Coupon;

public interface CouponClient {

    Coupon getCoupon(String code);
}
