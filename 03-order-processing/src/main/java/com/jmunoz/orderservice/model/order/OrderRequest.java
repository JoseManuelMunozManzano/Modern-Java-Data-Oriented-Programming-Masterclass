package com.jmunoz.orderservice.model.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// DTO de entrada a nuestro servicio.
// Contiene validaciones como anotaciones. En vez de anotaciones, podríamos crear validaciones usando compact constructor.
// Las validaciones son de formato de la petición, es decir, que venga o no venga algo, valor mínimo o máximo esperados...
// Que exista ese algo (por ejemplo, que exista customerId) en la aplicación ya lo hace el orchestrator.
public record OrderRequest(@NotBlank String customerId,
                           @NotBlank String productId,
                           @Min(1) int quantity) {
}
