package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpTimeoutException;

public class Lec07RecordPattern {

    private static final Logger log = LoggerFactory.getLogger(Lec07RecordPattern.class);

    // Hay mejores formas de modelar esto, y lo veremos más adelante.
    // Aquí lo hacemos así para esta clase.
    record ApiResponse<T>(T success,
                          Throwable error) {

    }

    static void main() {
        patternMatch(new ApiResponse<>("sam", null));
        patternMatch(new ApiResponse<>(50, null));
        patternMatch(new ApiResponse<>(null, new HttpTimeoutException("unable to reach google.com")));
        patternMatch(new ApiResponse<>(null, null));
    }

    // Las clases record pueden deconstruirse y realizar también pattern matching sobre los componentes del record.
    // No podemos deconstruir ni una clase, ni una lista, etc. SOLO RECORDS.
    private static void patternMatch(ApiResponse<?> response) {
        switch (response) {
            case ApiResponse(Integer data, _) -> log.info("int data: {}", data);
            case ApiResponse(String data, _) -> log.info("string data: {}", data);
            case ApiResponse(_, HttpTimeoutException ex) -> log.error("timed out: {}", ex.getMessage());
            case null, default -> log.error("unexpected: {}", response);
        };
    }
}
