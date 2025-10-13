package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

// Ejemplo con Compact Constructor, que arregla el problema de construir Canonical Constructor.
// Compact Constructor es una versión abreviada del Canonical Constructor.
public class Lec03CompactConstructor {

    private static final Logger log = LoggerFactory.getLogger(Lec03CompactConstructor.class);

    record Person(String firstName,
                  String lastName){

        // Como parte de un Compact Constructor, eliminamos to-do, parámetros de entrada y tipo de acceso public.
        // De la codificación, eliminamos this (ver por qué en la parte de teoría de esta clase, en README.md)
        // Podemos acceder a todos los campos en este compact constructor cuando se inicializa un objeto, y, en
        // la codificación, no hace falta hacer una asignación manual a cada campo (se hace automáticamente), solo
        // a los campos que queremos modificar.
        // Este compact constructor es perfecto para añadir validaciones, ya que se ejecuta primero.
        Person {
            // Imaginemos que, como parte de nuestro negocio, el apellido siempre tiene que ir en mayúsculas y,
            // además, es obligatorio.
            Objects.requireNonNull(lastName, "Lastname can not be null");
            lastName = lastName.toUpperCase();
        }
    }

    static void main() {
        var person = new Person("john", "doe");
        log.info("{}", person);

        // Si no pasa la validación
        var personNull = new Person("john", null);
        log.info("{}", personNull);
    }
}
