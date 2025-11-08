package com.jmunoz.sec08.lec03;

// En este escenario (Jackson mixin) no usaremos anotaciones.
// Imaginemos que este sealed type es de una librería de terceros y no añadieron las anotaciones.
public sealed interface ContactType {

    record EMail(String address) implements ContactType {
    }

    record Phone(String countryCode,
                 String number) implements ContactType {
    }
}
