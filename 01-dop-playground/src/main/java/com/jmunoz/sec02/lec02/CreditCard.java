package com.jmunoz.sec02.lec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Los records son implícitamente final, por eso no hace falta indicarlo.
public record CreditCard(String number) implements Payment {

    private static final Logger log = LoggerFactory.getLogger(CreditCard.class);

    @Override
    public void process(int amount) {
        log.info("processing amount {} using CC {}", amount, this.number);
    }
}
