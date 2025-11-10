package com.jmunoz.orderservice.exception;

public sealed interface SystemError extends ApplicationError {

    // Este error es genérico.
    record RemoteServiceError(String service,
                              String message) implements SystemError {
    }
}
