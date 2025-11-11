package com.jmunoz.sec06.lec01;

import java.util.Objects;

// Vamos a modelar que el valor puede o no estar presente.
// Es una reescritura de Optional usando sealed interface.
// No es exhaustivo, es para entender como modelar incertidumbre.
public sealed interface Option<T> {

    record Present<T>(T value) implements Option<T> {
    }

    // Como Absent no tiene ningún record component, no hace falta estar recreando
    // la instancia en los méto-dos helper. Solo creamos aquí una instancia y la
    // usaremos en todos lados.
    record Absent<T>() implements Option<T> {
        private static final Option<?> ABSENT = new Absent<>();
    }

    static <T> Option<T> data(T value) {
        return Objects.nonNull(value) ? new Present<>(value) : absent();
    }

    // Este méto-do estático helper emula Optional.empty()
    @SuppressWarnings("unchecked")
    static <T> Option<T> absent() {
        return (Option<T>) Absent.ABSENT;
    }

    // Este méto-do helper emula Optional.isPresent()
    default boolean isPresent() {
        return this instanceof Option.Present<T>;
    }

    // Este méto-do helper emula Optional.orElse()
    // Si hay Present, devolvemos ese valor. Si es Absent devolvemos este defaultValue.
    default T orElse(T defaultValue) {
        return switch (this) {
            case Present(T value) -> value;
            case Absent() -> defaultValue;
        };
    }
}
