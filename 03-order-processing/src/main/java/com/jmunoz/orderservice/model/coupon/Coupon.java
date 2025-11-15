package com.jmunoz.orderservice.model.coupon;

import java.util.Objects;

public sealed interface Coupon {

    record Flat(String code,
                Integer discount) implements Coupon {
    }

    // Puede que no venga maxDiscount en la respuesta.
    // Se puede crear otro tipo para separarlo, pero he decidido hacer un compact constructor.
    record Percentage(String code,
                      Integer percent,
                      Integer maxDiscount) implements Coupon {

        // Si maxDiscount no está presente, vamos a asumir el descuento máximo.
        public Percentage {
            maxDiscount = Objects.requireNonNullElse(maxDiscount, Integer.MAX_VALUE);
        }
    }

    // Aunque los dos tipos de cupones son los de arriba, indicamos un tipo más.
    // Si el cupón está presente, será uno de los de arriba.
    // Si el cupón no está presente, lo modelamos aquí.
    // Un record sin componentes, crear un objeto cada vez puede verse como innecesario, así
    // que lo hacemos Singleton.
    record None() implements Coupon {
        private static final Coupon NONE = new None();
    }

    // Helper para devolver un Coupon None.
    static Coupon none() {
        return None.NONE;
    }
}
