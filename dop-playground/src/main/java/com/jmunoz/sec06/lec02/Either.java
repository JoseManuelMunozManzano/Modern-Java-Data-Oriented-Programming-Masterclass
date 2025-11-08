package com.jmunoz.sec06.lec02;

// Modelamos el tipo Either para que devuelva un tipo de dato u otro.
public sealed interface Either<L, R> {

    record Left<L, R>(L value) implements Either<L, R> {
    }

    record Right<L, R>(R value) implements Either<L, R> {
    }

    // Métodos estáticos helper.
    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }
}
