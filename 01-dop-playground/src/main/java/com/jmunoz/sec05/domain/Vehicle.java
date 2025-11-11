package com.jmunoz.sec05.domain;

public sealed interface Vehicle {

    // Como make es común a todas las implementaciones, la creamos aquí como méto-do abstracto.
    // No es obligatorio crearla aquí.
    String make();

    record Car(String make,
               int year) implements Vehicle {
    }

    record Motorcycle(String make,
                      int engineCC) implements Vehicle {
    }
}
