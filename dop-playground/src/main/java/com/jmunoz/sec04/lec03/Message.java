package com.jmunoz.sec04.lec03;

import java.util.Objects;

public record Message(String value) {

    // En el compact constructor validaremos la data para que, una vez dentro del sistema,
    // estemos seguros de que es data válida y bien formada.
    public Message {
        if (Objects.isNull(value) || value.length() < 10 || value.length() > 5000) {
            throw new IllegalArgumentException("Not a valid message");
        }
    }
}
