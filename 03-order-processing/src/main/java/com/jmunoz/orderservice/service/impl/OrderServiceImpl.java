package com.jmunoz.orderservice.service.impl;

import com.jmunoz.orderservice.model.order.OrderRequest;
import com.jmunoz.orderservice.model.order.OrderResponse;
import com.jmunoz.orderservice.orchestrator.OrderOrchestrator;
import com.jmunoz.orderservice.orchestrator.OrderState;
import com.jmunoz.orderservice.service.OrderService;
import com.jmunoz.orderservice.util.DomainDtoMapper;

// Convertimos OrderRequest en CreateOrderCommand.
// Cuando lleguemos al estado Fulfilled, devolvemos OrderResponse.
// Necesitamos hacer varios mapeos. Ver el paquete util, clase DomainDtoMapper.
public class OrderServiceImpl implements OrderService {

    // Inyectado por el constructor.
    private final OrderOrchestrator orderOrchestrator;

    public OrderServiceImpl(OrderOrchestrator orderOrchestrator) {
        this.orderOrchestrator = orderOrchestrator;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        var command = DomainDtoMapper.toCreateOrderCommand(request);
        var placedOrderState = new OrderState.Placed(command);
        var orderState = this.orderOrchestrator.orchestrate(placedOrderState);
        // No esperamos más que un caso, así que si viniera otra cosa (no debería ocurrir nunca), lanzamos una excepción.
        // Las excepciones que esperamos ya las controlamos y usaremos un controller advice.
        // Lo hacemos con switch porque en el futuro podríamos introducir nuevos OrderState, como Cancelled o Refunded...
        return switch (orderState){
            case OrderState.Fulfilled fulfilled -> DomainDtoMapper.toOrderResponse(fulfilled.order(), fulfilled.invoice(), fulfilled.shipments());
            default -> throw new IllegalStateException("Unexpected value: " + orderState);
        };
    }
}
