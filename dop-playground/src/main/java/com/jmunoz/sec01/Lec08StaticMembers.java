package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec08StaticMembers {

    private static final Logger log = LoggerFactory.getLogger(Lec08StaticMembers.class);

    record Price(double amount,
                 String currency) {

        // Podemos crear miembros estáticos.
        private static final String USD = "$";

        // Podemos crear métodos factory estáticos.
        static Price usd(double amount) {
            return new Price(amount, USD);
        }
    }

    static void main() {

        log.info("{}", Price.usd(10.50));
    }
}
