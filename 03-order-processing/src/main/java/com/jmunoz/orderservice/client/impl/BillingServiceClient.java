package com.jmunoz.orderservice.client.impl;

import com.jmunoz.orderservice.client.BillingClient;
import com.jmunoz.orderservice.model.invoice.Invoice;
import com.jmunoz.orderservice.model.invoice.InvoiceRequest;
import com.jmunoz.orderservice.model.invoice.InvoiceRequest.*;
import org.springframework.web.client.RestClient;

import java.util.Collections;

public class BillingServiceClient extends AbstractServiceClient implements BillingClient {

    // Inyectado por el constructor.
    private final RestClient restClient;

    public BillingServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected String getServiceName() {
        return "billing-service";
    }

    @Override
    public Invoice createInvoice(InvoiceRequest request) {
        // Tenemos dos tipos de peticiones:
        //      http://localhost:7070/billing/invoices/paid
        //      http://localhost:7070/billing/invoices/unpaid
        // La url base es http://localhost:7070/billing y el resto es el uri.
        return switch (request) {
            case Paid _ -> this.executeRequest("/invoices/paid", request);
            case Unpaid _ -> this.executeRequest("/invoices/unpaid", request);
        };
    }

    private Invoice executeRequest(String path, InvoiceRequest request) {
        // executeRequest() es el méto-do no abstracto de AbstractServiceClient.
        return this.executeRequest(
                () -> this.restClient.post()
                        .uri(path)
                        .body(request)
                        .retrieve()
                        .body(Invoice.class),

                // En cuanto a errores, no tenemos nada especial que gestionar.
                Collections.emptyMap()
        );
    }

    @Override
    public void cancelInvoice(String invoiceId) {
        // La url base es http://localhost:7070/billing y el resto es el uri.
        this.executeRequest(
                () -> this.restClient.post()
                        .uri("/invoices/{invoiceId}/cancel", invoiceId)
                        .retrieve()  // No hay body, así que directamente recuperamos.
                        .toBodilessEntity(), // No esperamos ninguna respuesta.
                Collections.emptyMap()  // No tenemos errorMap.
        );
    }
}
