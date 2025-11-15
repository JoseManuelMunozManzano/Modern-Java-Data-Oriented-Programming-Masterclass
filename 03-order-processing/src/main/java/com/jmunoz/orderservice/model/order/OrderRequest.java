package com.jmunoz.orderservice.model.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// DTO de entrada a nuestro servicio.
// Contiene validaciones como anotaciones. En vez de anotaciones, podríamos crear validaciones usando compact constructor.
// Las validaciones son de formato de la petición, es decir, que venga o no venga algo, valor mínimo o máximo esperados...
// Que exista ese algo (por ejemplo, que exista customerId) en la aplicación ya lo hace el orchestrator.
//
// Para la fase 2 añadimos couponCode. Este puede o no estar, pero aquí no se representa con un Optional porque
// este DTO representa la entrada cruda (raw) de la petición, así que puede ser null perfectamente.
// Es en la clase CreateOrderCommand donde tratamos ya couponCode como un Optional.
// Dicho esto, tampoco pasa nada si aquí creamos couponCode también como un Optional.
public record OrderRequest(@NotBlank String customerId,
                           @NotBlank String productId,
                           @Min(1) int quantity,
                           String couponCode) {
}
