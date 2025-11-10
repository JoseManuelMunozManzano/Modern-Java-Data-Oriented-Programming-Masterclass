package com.jmunoz.orderservice.client.impl;

import com.jmunoz.orderservice.exception.ApplicationExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

abstract class AbstractServiceClient {

    private static final Logger log = LoggerFactory.getLogger(AbstractServiceClient.class);

    protected abstract String getServiceName();

    // Intentamos ejecutar el supplier, que, como no sabemos de qué tipo es, usamos generics.
    // En caso de excepción, usamos el Map, que significa:
    //   Para error 400 ¿cuál es la respuesta a dar?
    //   Para error 500 ¿cuál es la respuesta a dar?
    // Este Map lo tiene que proveer el que llame y con él devolveremos el valor apropiado.
    // También puede ser un map null (no enviarse) o no tener valor para esa key (el status code).
    //   En ese caso, devolvemos remoteServiceError.
    protected <T> T executeRequest(Supplier<T> supplier, Map<Integer, Supplier<T>> errorMap) {
        try {
            var t = supplier.get();
            log.info("response: {}", t);
            return t;
        } catch (HttpStatusCodeException ex) {
            log.error("error response from {}", this.getServiceName(), ex);
            // El Map, como se ha dicho, puede ser null, de ahí el Optional.
            // Para ese status, si hay un supplier, lo obtenemos.
            // Si no hay supplier, obtenemos el error de ApplicationExceptions.
            return Optional.ofNullable(errorMap.get(ex.getStatusCode().value()))
                    .map(Supplier::get)
                    .orElseGet(() -> ApplicationExceptions.remoteServiceError(this.getServiceName(), ex.getMessage()));
        }
    }
}
