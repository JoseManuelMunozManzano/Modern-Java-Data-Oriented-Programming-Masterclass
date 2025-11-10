package com.jmunoz.orderservice.orchestrator.impl;

import com.jmunoz.orderservice.orchestrator.OrderOrchestrator;
import com.jmunoz.orderservice.orchestrator.OrderState;
import com.jmunoz.orderservice.service.PaymentBillingService;
import com.jmunoz.orderservice.service.PriceCalculator;
import com.jmunoz.orderservice.service.RequestValidatorService;
import com.jmunoz.orderservice.service.ShippingService;

public class OrderOrchestratorImpl implements OrderOrchestrator {

    // Estos son los servicios que llamarán a los clientes.
    // Inyectados por el constructor.
    private final RequestValidatorService validatorService;
    private final PriceCalculator priceCalculator;
    private final PaymentBillingService paymentBillingService;
    private final ShippingService shippingService;

    public OrderOrchestratorImpl(RequestValidatorService validatorService, PriceCalculator priceCalculator, PaymentBillingService paymentBillingService, ShippingService shippingService) {
        this.validatorService = validatorService;
        this.priceCalculator = priceCalculator;
        this.paymentBillingService = paymentBillingService;
        this.shippingService = shippingService;
    }

    @Override
    public OrderState handle(OrderState.Placed placed) {
        // En caso de error al obtener la orden, se lanza un RuntimeException que tenemos controlado.
        var order = this.validatorService.validate(placed.request());
        return new OrderState.Validated(order);
    }

    @Override
    public OrderState handle(OrderState.Validated validated) {
        var priceSummary = this.priceCalculator.calculate(validated.order());
        return new OrderState.Priced(validated.order(), priceSummary);
    }

    @Override
    public OrderState handle(OrderState.Priced priced) {
        var invoice = this.paymentBillingService.processPayment(priced.order(), priced.priceSummary());
        return new OrderState.Invoiced(priced.order(), invoice);
    }

    @Override
    public OrderState handle(OrderState.Invoiced invoiced) {
        var shippingResponse = this.shippingService.scheduleShipping(invoiced.order());
        return new OrderState.Shipped(invoiced.order(), invoiced.invoice(), shippingResponse.shipments());
    }

    @Override
    public OrderState handle(OrderState.Shipped shipped) {
        return new OrderState.Fulfilled(shipped.order(), shipped.invoice(), shipped.shipments());
    }
}
