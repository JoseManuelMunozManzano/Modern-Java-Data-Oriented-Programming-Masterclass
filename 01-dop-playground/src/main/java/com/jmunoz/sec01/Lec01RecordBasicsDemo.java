package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Creamos un record dentro de esta clase y exploramos lo básico de los records, como getters, equals, toString...
public class Lec01RecordBasicsDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec01RecordBasicsDemo.class);

    record Person(String firstName,
                  String lastName){
    }

    static void main() {
        var person1 = new Person("john", "doe");
        var person2 = new Person("john", "doe");
        var person3 = new Person("alice", "doe");

        // Getters y toString
        log.info("firstname: {}", person1.firstName());
        log.info("lastname: {}", person1.lastName());
        log.info("toString: {}", person1);

        // Diferencia entre == y equals()
        log.info("person1 == person2: {}", (person1 == person2));           // Posiciones de memoria diferentes --> false
        log.info("person1 equals person2: {}", person1.equals(person2));    // Mismos nombres y apellidos --> true
        log.info("person1 equals person3: {}", person1.equals(person3));    // Apellido distinto --> false

        // HashCode
        log.info("person1 hashcode: {}", person1.hashCode());
        log.info("person2 hashcode: {}", person2.hashCode());
        log.info("person3 hashcode: {}", person3.hashCode());
    }
}
