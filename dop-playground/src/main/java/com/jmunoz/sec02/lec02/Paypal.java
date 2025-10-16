package com.jmunoz.sec02.lec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Los records son implícitamente final, por eso no hace falta indicarlo.
public record Paypal(String email) implements Payment {

    private static final Logger log = LoggerFactory.getLogger(Paypal.class);

    @Override
    public void process(int amount) {
        log.info("processing amount {} using email {}", amount, this.email);
    }
}
