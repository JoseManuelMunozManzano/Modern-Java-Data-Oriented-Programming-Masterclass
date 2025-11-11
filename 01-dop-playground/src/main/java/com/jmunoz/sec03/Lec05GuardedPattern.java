package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec05GuardedPattern {

    private static final Logger log = LoggerFactory.getLogger(Lec05GuardedPattern.class);

    static void main() {
        patternMatch(5);
        patternMatch(-5);
        patternMatch((short)5);
        patternMatch(5.0);
        patternMatch(5.0f);
        patternMatch(5_000_000_000L);
    }

    // Requerimiento nuevo: Si enviamos un número negativo entero, queremos escribir que se ha enviado un número negativo.
    private static void patternMatch(Object object) {
        switch (object) {
            case Short s -> log.info("short {}", s);
            case Double d -> log.info("double {}", d);
            // El pattern variable i es accesible también en la cláusula guarda when.
            case Integer i when i < 0 -> log.info("negative int {}", i);
            case Integer i -> log.info("int {}", i);
            case Number n -> log.info("number {}", n);
            case null, default -> log.info("null/default: {}", object);
        };
    }
}
