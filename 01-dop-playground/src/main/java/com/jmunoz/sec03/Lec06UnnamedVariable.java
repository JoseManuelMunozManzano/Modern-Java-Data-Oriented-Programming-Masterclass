package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06UnnamedVariable {

    private static final Logger log = LoggerFactory.getLogger(Lec06UnnamedVariable.class);

    static void main() {
        patternMatch(5);
        patternMatch(5.0);
        patternMatch(5.0f);
    }

    // Requerimiento: Solo tenemos que indicar el tipo de variable recibido y no tenemos que hacer nada con el valor.
    // Podemos trabajar como hasta ahora, es decir mantener el pattern variable: case Double d
    // Podemos indicar como pattern variable la palabra clave ignored: case Double ignored
    // Podemos indicar como pattern variable el guion bajo, que indica que no hay pattern variable: case Double _
    private static void patternMatch(Object object) {
        switch (object) {
            case Double _ -> log.info("received double");
            case Integer _ -> log.info("received int");
            case null, default -> log.info("null/default: {}", object);
        };
    }
}
