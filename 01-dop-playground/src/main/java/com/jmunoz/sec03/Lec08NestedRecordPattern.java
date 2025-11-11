package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpTimeoutException;

public class Lec08NestedRecordPattern {

    private static final Logger log = LoggerFactory.getLogger(Lec08NestedRecordPattern.class);

    // Hay mejores formas de modelar esto, y lo veremos más adelante.
    // Aquí lo hacemos así para esta clase.
    record ApiResponse<T>(T success,
                          Throwable error) {

    }

    record Product(String name,
                   int price) {

    }

    record User(String name,
                String email) {
    }

    static void main() {
        patternMatch(new ApiResponse<>("sam", null));
        patternMatch(new ApiResponse<>(new Product("tv", 100), null));
        patternMatch(new ApiResponse<>(new User("sam", "sam@email.com"), null));
        patternMatch(new ApiResponse<>(null, new HttpTimeoutException("unable to reach google.com")));
        patternMatch(new ApiResponse<>(null, null));
    }

    // Las clases record pueden deconstruirse y realizar también pattern matching sobre los componentes del record.
    // No podemos deconstruir ni una clase, ni una lista, etc. SOLO RECORDS, y de forma recursiva (ver Product y User).
    private static void patternMatch(ApiResponse<?> response) {
        switch (response) {
            case ApiResponse(Product(var name, _), _) -> log.info("product name: {}", name);
            case ApiResponse(User(_, var email), _) -> log.info("user email: {}", email);
            // Sin deconstruir user quedaría:
            // case ApiResponse(User user, _) -> log.info("user email: {}", user.email());
            case ApiResponse(String data, _) -> log.info("string data: {}", data);
            case ApiResponse(_, HttpTimeoutException ex) -> log.error("timed out: {}", ex.getMessage());
            case null, default -> log.error("unexpected: {}", response);
        };
    }
}
