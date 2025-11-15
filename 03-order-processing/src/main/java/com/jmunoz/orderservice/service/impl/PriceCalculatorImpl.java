package com.jmunoz.orderservice.service.impl;

import com.jmunoz.orderservice.model.common.PriceSummary;
import com.jmunoz.orderservice.model.coupon.Coupon;
import com.jmunoz.orderservice.model.coupon.Coupon.*;
import com.jmunoz.orderservice.model.order.Order;
import com.jmunoz.orderservice.model.product.Product.*;
import com.jmunoz.orderservice.service.PriceCalculator;

public class PriceCalculatorImpl implements PriceCalculator {

    @Override
    public PriceSummary calculate(Order order) {
        var state = order.customer().address().state();
        var quantity = order.orderItem().quantity();
        return switch (order.orderItem().product()) {
            case Single single -> this.toPriceSummary(single.price(), single.price(), quantity, state, order.coupon());
            case Bundle bundle -> this.toPriceSummary(bundle.originalPrice(), bundle.discountedPrice(), quantity, state, order.coupon());
        };
    }

    // Calculamos el monto total teniendo en cuenta los descuentos del cupón.
    private double applyCoupon(Coupon coupon, double amount) {
        var payableAmount = switch (coupon) {
            case None _ -> amount;
            case Flat flat -> amount - flat.discount();
            case Percentage percentage -> this.applyPercentageCoupon(percentage, amount);
        };
        // Es posible que, al restar los descuentos, el importe salga negativo.
        // Si es negativo, se paga 5 dólares.
        return Math.max(5, payableAmount);
    }

    private double applyPercentageCoupon(Percentage percentage, double amount) {
        var discounted = (percentage.percent() / 100d) * amount;
        // Cuidado, porque puede haber un descuento máximo.
        // Cogemos el mínimo entre el descuento y el máximo descuento.
        var maxDiscount = Math.min(discounted, percentage.maxDiscount());
        return amount - maxDiscount;
    }

    private PriceSummary toPriceSummary(double unitPrice, double discountedPrice, int quantity, String state, Coupon coupon) {
        var subTotal = unitPrice * quantity;
        var discountedAmount = this.applyCoupon(coupon, discountedPrice * quantity);
        var tax = discountedAmount * getTaxRate(state);
        return new PriceSummary(
                subTotal,
                subTotal - discountedAmount,
                tax,
                discountedAmount + tax
        );
    }

    private double getTaxRate(String state) {
        return switch (state) {
            case "MI" -> 0.06;
            case "WA" -> 0.09;
            case "FL" -> 0.07;
            case "TX", "NY", "CA", "IL", "AZ" -> 0.08;
            default -> 0.05;    // Default tax rate
        };
    }
}
