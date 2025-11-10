package com.jmunoz.orderservice.client.impl;

import com.jmunoz.orderservice.client.CustomerClient;
import com.jmunoz.orderservice.exception.ApplicationExceptions;
import com.jmunoz.orderservice.model.customer.Customer;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.function.Supplier;

public class CustomerServiceClient extends AbstractServiceClient implements CustomerClient {

    // Inyectado por el constructor.
    private final RestClient restClient;

    public CustomerServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    // Este es el méto-do abstracto a implementar de AbstractServiceClient.
    @Override
    protected String getServiceName() {
        return "customer-service";
    }

    // En caso de error 400 o 500, se lanzará una excepción.
    // No me gusta hacer el bloque try...catch aquí.
    // Por eso creamos una clase abstracta que gestione el manejo de errores (AbstractServiceClient)
    @Override
    public Customer getCustomer(String customerId) {
        // Este es el map que usa AbstractServiceClient, en caso de error al obtener el resultado del servicio.
        var errorMap = Map.<Integer, Supplier<Customer>>of(
                404, () -> ApplicationExceptions.customerNotFound(customerId)
        );

        // La url base es http://localhost:7070/customers y el resto es el uri.
        // executeRequest() es el méto-do no abstracto de AbstractServiceClient.
        // Se intenta ejecutar la llamada al servicio y si hay algún error, indicamos el map de error.
        return this.executeRequest(
                () -> this.restClient.get()
                        .uri("/{customerId}", customerId)
                        .retrieve()
                        .body(Customer.class),
                errorMap
        );
    }
}
