package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Cuando usamos `switch` nos obliga a añadir el bloque `default`, porque piensa que no hemos
// cubierto todos los posibles valores de entrada.
//
// Pero en algunos casos sí que podemos cubrir todos los posibles valores de entrada,
// usando un enum y, en ese caso, no hace falta que usemos `default`.
public class Lec09Exhaustiveness {

    private static final Logger log = LoggerFactory.getLogger(Lec09Exhaustiveness.class);

    // Estos son todos los posibles valores de entrada.
    enum Country {
        US,
        UK,
        CA,
        AU
    }

    static void main() {
        // En el front se evita que llegue un null como Country.
        log.info("{}", getTax(Country.US, 100));
        log.info("{}", getTax(Country.UK, 100));
        log.info("{}", getTax(Country.CA, 100));
        log.info("{}", getTax(Country.AU, 100));
    }

    // No hace falta el caso default porque cubrimos todos los valores posibles de entrada.
    // Podemos añadir el caso null si pensamos que nos puede llegar ese caso, para que no falle por NullPointerException.
    private static double getTax(Country country, Integer price) {
        var taxRate = switch (country) {
            case US -> 0.05;
            case UK, AU -> 0.06;
            case CA -> 0.08;
        };
        log.info("country: {}, taxRate: {}", country, taxRate);
        return taxRate * price;
    }
}
