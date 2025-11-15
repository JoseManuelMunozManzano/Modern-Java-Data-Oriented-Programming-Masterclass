package com.jmunoz.orderservice.client.impl;

import com.jmunoz.orderservice.client.PaymentClient;
import com.jmunoz.orderservice.model.payment.PaymentRequest;
import com.jmunoz.orderservice.model.payment.PaymentStatus;
import com.jmunoz.orderservice.model.payment.RefundRequest;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

public class PaymentServiceClient extends AbstractServiceClient implements PaymentClient {

    // Inyectado por el constructor.
    private final RestClient restClient;

    public PaymentServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    // Este es el méto-do abstracto a implementar de AbstractServiceClient.
    @Override
    protected String getServiceName() {
        return "payment-service";
    }

    // En caso de error 400 o 500, se lanzará una excepción.
    // No me gusta hacer el bloque try...catch aquí.
    // Por eso creamos una clase abstracta que gestione el manejo de errores (AbstractServiceClient)
    @Override
    public PaymentStatus process(PaymentRequest request) {
        // Desde el punto de vista del negocio, el status 402 no es un error, y el programa continúa perfectamente
        // generando una respuesta PaymentStatus.
        var errorMap = Map.<Integer, Supplier<PaymentStatus>>of(
                402, () -> new PaymentStatus.Declined(request.orderId(), request.amount())
        );

        // La url base es http://localhost:7070/payments y el resto es el uri.
        // executeRequest() es el méto-do no abstracto de AbstractServiceClient.
        // Se intenta ejecutar la llamada al servicio y si hay algún error, indicamos el map de error.
        return this.executeRequest(
                () -> this.restClient.post()
                        .uri("/process")
                        .body(request)
                        .retrieve()
                        .body(PaymentStatus.Processed.class),
                errorMap
        );
    }

    @Override
    public void refund(RefundRequest request) {
        // La url base es http://localhost:7070/payment y el resto es el uri.
        this.executeRequest(
                () -> this.restClient.post()
                        .uri("/refund")
                        .body(request)
                        .retrieve()
                        .toBodilessEntity(),  // No esperamos ninguna respuesta.
                Collections.emptyMap() // No tenemos errorMap.
        );
    }
}
