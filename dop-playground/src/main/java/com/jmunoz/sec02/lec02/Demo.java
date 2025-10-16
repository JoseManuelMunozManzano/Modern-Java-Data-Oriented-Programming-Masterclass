package com.jmunoz.sec02.lec02;

public class Demo {

    static void main() {
        processPayment(new CreditCard("123-456-789"));
        processPayment(new Paypal("sam@gmail.com"));
    }

    private static void processPayment(Payment payment) {
        payment.process(100);
    }
}
