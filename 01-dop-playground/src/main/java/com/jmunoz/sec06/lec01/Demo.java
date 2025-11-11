package com.jmunoz.sec06.lec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    static void main() {
        log.info("present: {}", getCustomer(1).orElse("missing"));
        log.info("missing: {}", getCustomer(8).orElse("missing"));
    }

    // Solo para mantenerlo simple. Esto emula una llamada a BD.
    // Si el id es 1 devolvemos sam, en caso contrario devolvemos que no hay customer.
    private static Option<String> getCustomer(int id) {
        return id == 1 ? Option.data("sam") : Option.absent();
    }
}
