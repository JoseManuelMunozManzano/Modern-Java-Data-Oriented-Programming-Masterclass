package com.jmunoz.sec04.lec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    static void main() {
        handlePayment(new Payment.CreditCard("123-456-789", "123"));
        handlePayment(new Payment.Paypal("sam@email.com"));
    }

    // Imaginemos que este es el servicio payment.
    // Dependiendo del tipo de Payment, se va a comportar o va a proceder de una forma concreta.
    private static void handlePayment(Payment payment) {
        switch (payment) {
            case Payment.CreditCard creditCard -> log.info("processing credit card {}", creditCard.number());
            case Payment.Paypal paypal -> log.info("processing paypal {}", paypal.email());
        }
    }
}
