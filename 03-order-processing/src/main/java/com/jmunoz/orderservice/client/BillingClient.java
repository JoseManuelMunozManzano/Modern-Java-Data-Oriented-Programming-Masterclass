package com.jmunoz.orderservice.client;

import com.jmunoz.orderservice.model.invoice.Invoice;
import com.jmunoz.orderservice.model.invoice.InvoiceRequest;

public interface BillingClient {

    Invoice createInvoice(InvoiceRequest request);

    void cancelInvoice(String invoiceId);
}
