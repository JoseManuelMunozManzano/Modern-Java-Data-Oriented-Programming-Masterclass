package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec02SwitchExpression {

    private static final Logger log = LoggerFactory.getLogger(Lec02SwitchExpression.class);

    static void main() {
        log.info("{}", getTax("US", 100));
        log.info("{}", getTax("UK", 100));
        log.info("{}", getTax("CA", 100));
        log.info("{}", getTax("AU", 100));
        log.info("{}", getTax(null, 100));  // NullPointerException si no se hace case null
    }

    // Cuando el case coincide con country, devuelve el valor indicado tras ->
    // No hay necesidad de break ni de estar asignando una variable en cada case.
    // En caso de necesitar más código, este se puede indicar entre llaves y para devolver el valor se usa yield.
    // Puede implementarse un case null que devuelva un valor o lanzar una excepción personalizada.
    // El caso default sigue siendo necesario (si no se cubren todos las posibles entradas. Ver Lec09Exhaustiveness)
    //
    // NOTA: Los switch statement no tienen por qué devolver algo siempre. Podemos indicar sentencias. Por ejemplo:
    //  switch(country) {
    //    case "US" -> log.info("United States");
    //    default -> log.info("Unknown {}", country);
    //  };
    private static double getTax(String country, Integer price) {
        var taxRate = switch (country) {
            case "US" -> 0.05;
            case "UK", "AU" -> 0.06;
            // case null, default -> 0.08;
            case null -> throw new IllegalArgumentException("country can not be null");
            default -> {
                log.info("default tax rate is used for country: {}", country);
                yield 0.08;
            }
        };
        log.info("country: {}, taxRate: {}", country, taxRate);
        return taxRate * price;
    }
}
