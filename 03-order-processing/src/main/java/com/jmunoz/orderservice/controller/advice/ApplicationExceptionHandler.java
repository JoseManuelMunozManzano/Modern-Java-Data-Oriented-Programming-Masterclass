package com.jmunoz.orderservice.controller.advice;

import com.jmunoz.orderservice.exception.ApplicationError;
import com.jmunoz.orderservice.exception.ApplicationException;
import com.jmunoz.orderservice.exception.DomainError.*;
import com.jmunoz.orderservice.exception.SystemError.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.function.Consumer;

// Esta clase es la responsable de gestionar las excepciones de la aplicación para proveer respuestas apropiadas.
@ControllerAdvice
public class ApplicationExceptionHandler {

    // Dependiendo del error de la aplicación, como domain error, entity not found, system error... construiremos
    // el ProblemDetail apropiado.
    @ExceptionHandler(ApplicationException.class)
    public ProblemDetail handleException(ApplicationException ex) {
        return switch (ex.getApplicationError()) {
            case EntityNotFound error -> this.toProblemDetail(error);
            case PaymentDeclined error -> this.toProblemDetail(error);
            case ShippingDeclined error -> this.toProblemDetail(error);
            case ProductDiscontinued error -> this.toProblemDetail(error);
            case RemoteServiceError error -> this.toProblemDetail(error);
        };
    }

    // Aquí podríamos tener también otro méto-do con el exception handler para MethodArgumentNotValidException, para poder
    // construir nuestro propio Problem Detail con más información.
    // Ver postman, prueba 01-customer-id-null y el log.

    private ProblemDetail toProblemDetail(EntityNotFound error) {
        return this.build(HttpStatus.BAD_REQUEST, error, problemDetail -> {
            problemDetail.setTitle("Not Found");
            problemDetail.setDetail("Unable to find the requested entity %s for the given id %s".formatted(error.entity(), error.id()));
        });
    }

    private ProblemDetail toProblemDetail(PaymentDeclined error) {
        return this.build(HttpStatus.PAYMENT_REQUIRED, error, problemDetail -> {
            problemDetail.setTitle("Payment Required");
            problemDetail.setDetail("Payment for the order was declined. Please update your payment information and try again");
        });
    }

    private ProblemDetail toProblemDetail(ShippingDeclined error) {
        return this.build(HttpStatus.UNPROCESSABLE_ENTITY, error, problemDetail -> {
            problemDetail.setTitle("Unable To Ship");
            problemDetail.setDetail("We are unable to ship this order. Please contact support for more details");
        });
    }

    private ProblemDetail toProblemDetail(ProductDiscontinued error) {
        return this.build(HttpStatus.BAD_REQUEST, error, problemDetail -> {
            problemDetail.setTitle("Product Discontinued");
            problemDetail.setDetail("The product is discontinued. Check out our top-selling alternatives in the same category");
        });
    }

    private ProblemDetail toProblemDetail(RemoteServiceError error) {
        return this.build(HttpStatus.SERVICE_UNAVAILABLE, error, problemDetail -> {
            problemDetail.setTitle("Service Unavailable");
            problemDetail.setDetail("Unable to fulfill the order. Please try again later");
        });
    }

    private ProblemDetail build(HttpStatus status, ApplicationError error, Consumer<ProblemDetail> consumer) {
        var problem = ProblemDetail.forStatus(status);
        problem.setProperty("additionalInformation", error);
        consumer.accept(problem);
        return problem;
    }
}
