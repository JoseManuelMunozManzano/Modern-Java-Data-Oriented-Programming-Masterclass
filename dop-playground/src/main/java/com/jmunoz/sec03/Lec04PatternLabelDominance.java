package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec04PatternLabelDominance {

    private static final Logger log = LoggerFactory.getLogger(Lec04PatternLabelDominance.class);

    static void main() {
        patternMatch(5);        // Constantes literales por defecto son int.
        patternMatch((short)5);        // Si hacemos un cast a short, entonces será short.
        patternMatch(5.0);
        patternMatch(5.0f);
        patternMatch(5_000_000_000L);
    }

    // Si varios cases cumplen la condición, ¿cuál se ejecuta?
    // El funcionamiento de Pattern Matching es:
    //   Cuando recibimos el objeto, primero comprueba si el objeto dado es de tipo Double. Si lo es se ejecuta ese bloque.
    //   Si no lo es, va al siguiente case y comprueba si es de tipo Integer. Si lo es se ejecuta ese bloque.
    // Es decir, solo un bloque se ejecutará.
    private static void patternMatch(Object object) {
        switch (object) {
            case Short s -> log.info("short {}", s);
            case Double d -> log.info("double {}", d);
            case Integer i -> log.info("int {}", i);
            // Si subimos case Number encima de Integer o de Double o Short, veremos errores.
            // Esto es porque Number es una superclase. Double, Short e Integer son subclases.
            // Por tanto, si Number está por encima siempre se va a ejecutar y el compilador
            // nos avisa de que ni Double ni Integer se van a ejecutar jamás.
            case Number n -> log.info("number {}", n);
            case null, default -> log.info("null/default: {}", object);
        };
    }
}
