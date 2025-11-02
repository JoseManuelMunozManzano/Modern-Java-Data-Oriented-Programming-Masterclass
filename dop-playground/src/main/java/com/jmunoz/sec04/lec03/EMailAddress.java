package com.jmunoz.sec04.lec03;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public record EMailAddress(String value) {

    // Expresión regular. No es robusta, pero sirve para nuestro ejemplo.
    // Si la cadena dada no coincide con la condición, entonces es un email inválido.
    private static final Predicate<String> IS_INVALID_EMAIL = Pattern.compile("^[a-z]+@[a-z.]+$").asMatchPredicate().negate();

    // En el compact constructor validaremos la data para que, una vez dentro del sistema,
    // estemos seguros de que es data válida y bien formada.
    public EMailAddress {
        if (IS_INVALID_EMAIL.test(value)) {
            throw new IllegalArgumentException("Not a valid email address");
        }
    }
}
