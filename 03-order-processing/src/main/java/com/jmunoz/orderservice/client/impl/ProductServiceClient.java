package com.jmunoz.orderservice.client.impl;

import com.jmunoz.orderservice.client.ProductClient;
import com.jmunoz.orderservice.exception.ApplicationExceptions;
import com.jmunoz.orderservice.model.product.ProductStatus;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.function.Supplier;

public class ProductServiceClient extends AbstractServiceClient implements ProductClient {

    // Inyectado por el constructor.
    private final RestClient restClient;

    public ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    // Este es el méto-do abstracto a implementar de AbstractServiceClient.
    @Override
    protected String getServiceName() {
        return "product-service";
    }

    // En caso de error 400 o 500, se lanzará una excepción.
    // No me gusta hacer el bloque try...catch aquí.
    // Por eso creamos una clase abstracta que gestione el manejo de errores (AbstractServiceClient)
    @Override
    public ProductStatus getProductStatus(String productId) {
        // Este es el map que usa AbstractServiceClient, en caso de error al obtener el resultado del servicio.
        var errorMap = Map.<Integer, Supplier<ProductStatus>>of(
                404, () -> ApplicationExceptions.productNotFound(productId)
        );

        // La url base es http://localhost:7070/products y el resto es el uri.
        // executeRequest() es el méto-do no abstracto de AbstractServiceClient.
        // Se intenta ejecutar la llamada al servicio y si hay algún error, indicamos el map de error.
        return this.executeRequest(
                () -> this.restClient.get()
                        .uri("/{productId}", productId)
                        .retrieve()
                        .body(ProductStatus.class),
                errorMap
        );
    }
}
