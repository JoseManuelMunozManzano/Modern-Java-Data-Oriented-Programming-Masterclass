package com.jmunoz.sec02.lec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CashRewards extends Cash {

    private static final Logger log = LoggerFactory.getLogger(Cash.class);

    @Override
    public void process(int amount) {
        super.process(amount);
        // Por cada dólar, damos un punto.
        log.info("adding {} reward points", amount);
    }
}
