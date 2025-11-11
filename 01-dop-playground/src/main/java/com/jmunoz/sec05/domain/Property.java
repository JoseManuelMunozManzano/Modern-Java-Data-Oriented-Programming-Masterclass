package com.jmunoz.sec05.domain;

public sealed interface Property {

    // Como los dos types contienen Address, lo definimos fuera para forzar a todas las
    // implementaciones (las dos que tenemos) a que tengan esta property en común.
    // El accessor component lo implementa automáticamente.
    // No es obligatorio crearla.
    Address address();

    // Notar que si se comenta Address en este record, falla.
    record Residential(Address address,
                       int rooms) implements Property {
    }

    record Commercial(Address address,
                      BusinessType businessType) implements Property {
    }
}
