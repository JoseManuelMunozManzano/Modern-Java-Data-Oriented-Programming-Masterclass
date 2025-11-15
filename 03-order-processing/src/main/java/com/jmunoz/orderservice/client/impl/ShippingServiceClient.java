package com.jmunoz.orderservice.client.impl;

import com.jmunoz.orderservice.client.ShippingClient;
import com.jmunoz.orderservice.model.shipping.ShippingRequest;
import com.jmunoz.orderservice.model.shipping.ShippingResponse;
import com.jmunoz.orderservice.model.shipping.ShippingStatus;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

public class ShippingServiceClient extends AbstractServiceClient implements ShippingClient {

    // Inyectado por el constructor.
    private final RestClient restClient;

    public ShippingServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    // Este es el méto-do abstracto a implementar de AbstractServiceClient.
    @Override
    protected String getServiceName() {
        return "shipping-service";
    }

    // NOTA: Queda como histórico de la fase 1
    //
    // En caso de error 400 o 500, se lanzará una excepción.
    // No me gusta hacer el bloque try...catch aquí.
    // Por eso creamos una clase abstracta que gestione el manejo de errores (AbstractServiceClient)
//    @Override
//    public ShippingResponse schedule(ShippingRequest request) {
//        // La url base es http://localhost:7070/shipping y el resto es el uri.
//        // executeRequest() es el méto-do no abstracto de AbstractServiceClient.
//        // Se intenta ejecutar la llamada al servicio y si hay algún error, indicamos el map de error, que en este
//        // caso, no esperamos nada.
//        return this.executeRequest(
//                () -> this.restClient.post()
//                        .uri("/schedule")
//                        .body(request)
//                        .retrieve()
//                        .body(ShippingResponse.class),
//                Collections.emptyMap()
//        );
//    }

    // Ahora para la fase 3 se devuelve ShippingStatus.
    @Override
    public ShippingStatus schedule(ShippingRequest request) {
        // La url base es http://localhost:7070/shipping y el resto es el uri.
        // executeRequest() es el méto-do no abstracto de AbstractServiceClient.
        // Se intenta ejecutar la llamada al servicio y si hay algún error, indicamos el map de error.
        var errorMap = Map.<Integer, Supplier<ShippingStatus>>of(
                422, () -> new ShippingStatus.Declined(request.orderId())
        );
        return this.executeRequest(
                () -> this.restClient.post()
                        .uri("/schedule")
                        .body(request)
                        .retrieve()
                        .body(ShippingStatus.Scheduled.class),
                errorMap
        );
    }
}
