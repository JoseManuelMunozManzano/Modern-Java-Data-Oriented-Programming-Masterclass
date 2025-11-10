package com.jmunoz.orderservice.exception;

import com.jmunoz.orderservice.model.payment.PaymentStatus;
import com.jmunoz.orderservice.model.product.ProductStatus;

// Esta clase utility tiene como misión lanzar las distintas excepciones usando métodos static factory.
// La idea es mejorar la legibilidad de nuestra aplicación.
public class ApplicationExceptions {

    // En vez de void devolvemos el genérico T porque a veces querremos devolver un valor en vez de lanzar una excepción.
    public static <T> T customerNotFound(String id){
        var error = new DomainError.EntityNotFound(DomainError.Entity.CUSTOMER, id);
        throw new ApplicationException(error);
    }

    public static <T> T productNotFound(String id){
        var error = new DomainError.EntityNotFound(DomainError.Entity.PRODUCT, id);
        throw new ApplicationException(error);
    }

    public static <T> T discontinuedProduct(ProductStatus.Discontinued discontinued){
        var error = new DomainError.ProductDiscontinued(
                discontinued.productId(),
                discontinued.recommendedProducts()
        );
        throw new ApplicationException(error);
    }

    public static <T> T declinedPayment(PaymentStatus.Declined declined){
        var error = new DomainError.PaymentDeclined(
                declined.orderId(),
                declined.amount()
        );
        throw new ApplicationException(error);
    }

    public static <T> T remoteServiceError(String service, String message){
        var error = new SystemError.RemoteServiceError(service, message);
        throw new ApplicationException(error);
    }
}
