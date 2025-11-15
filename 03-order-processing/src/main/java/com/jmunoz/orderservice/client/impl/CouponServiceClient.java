package com.jmunoz.orderservice.client.impl;

import com.jmunoz.orderservice.client.CouponClient;
import com.jmunoz.orderservice.model.coupon.Coupon;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.function.Supplier;

public class CouponServiceClient extends AbstractServiceClient implements CouponClient {

    // Inyectado por el constructor.
    private final RestClient restClient;

    public CouponServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    // Este es el méto-do abstracto a implementar de AbstractServiceClient.
    @Override
    protected String getServiceName() {
        return "coupon-service";
    }

    // En caso de error 400 o 500, se lanzará una excepción.
    // No me gusta hacer el bloque try...catch aquí.
    // Por eso creamos una clase abstracta que gestione el manejo de errores (AbstractServiceClient)
    @Override
    public Coupon getCoupon(String code) {
        // Este es el map que usa AbstractServiceClient, en caso de error al obtener el resultado del servicio.
        var errorMap = Map.<Integer, Supplier<Coupon>>of(
                404, Coupon::none
        );

        // La url base es http://localhost:7070/coupons y el resto es el uri.
        // executeRequest() es el méto-do no abstracto de AbstractServiceClient.
        // Se intenta ejecutar la llamada al servicio y si hay algún error, indicamos el map de error.
        return this.executeRequest(
                () -> this.restClient.get()
                        .uri("/{code}", code)
                        .retrieve()
                        .body(Coupon.class),
                errorMap
        );
    }
}
