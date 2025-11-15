package com.jmunoz.orderservice.service.impl;

import com.jmunoz.orderservice.client.BillingClient;
import com.jmunoz.orderservice.client.PaymentClient;
import com.jmunoz.orderservice.exception.ApplicationExceptions;
import com.jmunoz.orderservice.model.common.PriceSummary;
import com.jmunoz.orderservice.model.customer.Customer.*;
import com.jmunoz.orderservice.model.invoice.Invoice;
import com.jmunoz.orderservice.model.invoice.Invoice.*;
import com.jmunoz.orderservice.model.invoice.InvoiceRequest;
import com.jmunoz.orderservice.model.order.Order;
import com.jmunoz.orderservice.model.payment.PaymentRequest;
import com.jmunoz.orderservice.model.payment.PaymentStatus.*;
import com.jmunoz.orderservice.model.payment.RefundRequest;
import com.jmunoz.orderservice.service.PaymentBillingService;

public class PaymentBillingServiceImpl implements PaymentBillingService {

    // Inyectados por el constructor.
    private final PaymentClient paymentClient;
    private final BillingClient billingClient;

    public PaymentBillingServiceImpl(PaymentClient paymentClient, BillingClient billingClient) {
        this.paymentClient = paymentClient;
        this.billingClient = billingClient;
    }

    @Override
    public Invoice processPayment(Order order, PriceSummary priceSummary) {
        var paymentRequest = new PaymentRequest(order.customer().id(), order.orderId(), priceSummary.finalAmount());
        var paymentStatus = this.paymentClient.process(paymentRequest);
        return switch (paymentStatus) {
            case Processed processed -> this.toPaidInvoice(order, priceSummary, processed);
            case Declined declined -> this.toUnpaidInvoice(order, priceSummary, declined);
        };
    }

    @Override
    public void refundPayment(Invoice invoice) {
        // Con esto cancelamos la orden.
        this.billingClient.cancelInvoice(invoice.id());
        // Solo tenemos que devolver el dinero al cliente si se le ha cobrado.
        switch (invoice) {
            case Paid paid -> this.refund(paid);
            case Unpaid _ -> {}
        };
    }

    private void refund(Paid paid) {
        var request = new RefundRequest(
                paid.customerId(),
                paid.orderId(),
                paid.priceSummary().finalAmount(),
                paid.transactionId()
        );
        this.paymentClient.refund(request);
    }

    private Invoice toPaidInvoice(Order order, PriceSummary priceSummary, Processed processed) {
        var request = new InvoiceRequest.Paid(order.orderId(), order.customer().id(), processed.transactionId(), priceSummary);
        return this.billingClient.createInvoice(request);
    }

    private Invoice toUnpaidInvoice(Order order, PriceSummary priceSummary, Declined declined) {
        return switch (order.customer()) {
            case Regular _ -> ApplicationExceptions.declinedPayment(declined);
            case Business business -> this.toUnpaidInvoice(order, priceSummary, business);
        };
    }

    private Invoice toUnpaidInvoice(Order order, PriceSummary priceSummary, Business business) {
        var request = new InvoiceRequest.Unpaid(
                order.orderId(),
                business.id(),
                business.name(),
                business.taxId(),
                priceSummary
        );

        return this.billingClient.createInvoice(request);
    }
}
