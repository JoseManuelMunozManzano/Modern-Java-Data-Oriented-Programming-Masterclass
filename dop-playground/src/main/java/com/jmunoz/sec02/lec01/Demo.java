package com.jmunoz.sec02.lec01;

public class Demo {

    static void main() {
        processPayment(new Cash());
        processPayment(new CreditCard());
        processPayment(new CashRewards());
    }

    private static void processPayment(Payment payment) {
        payment.process(100);
    }
}
