package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Ejemplo del constructor autogenerado por defecto, llamado Canonical Constructor y
// el problema que conlleva si el record tiene muchos campos y tenemos que escribirlo nosotros.
public class Lec02CanonicalConstructor {

    private static final Logger log = LoggerFactory.getLogger(Lec02CanonicalConstructor.class);

    record Person(String firstName,
                  String lastName){

        // Imaginemos que, como parte de nuestro negocio, lastname siempre tiene que estar en mayúsculas.
        // Escribimos el Canonical Constructor.
        // Tenemos que indicar todos los campos. ¿Qué pasa si tenemos 10 campos, tengo que indicarlos todos
        // solo para cambiar a mayúsculas uno de ellos? Esto es molesto y para evitarlo existe Compact Constructor.
        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName.toUpperCase();
        }
    }

    static void main() {
        var person = new Person("john", "doe");
        log.info("{}", person);
    }
}
