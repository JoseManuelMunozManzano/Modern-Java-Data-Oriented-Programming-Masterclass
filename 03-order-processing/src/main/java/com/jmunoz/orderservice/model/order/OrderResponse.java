package com.jmunoz.orderservice.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jmunoz.orderservice.model.common.PriceSummary;
import com.jmunoz.orderservice.model.shipping.Shipment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record OrderResponse(UUID orderId,
                            String status,
                            List<Product> products,
                            InvoiceDetails invoice,
                            List<Shipment> shipments) {

    // Para Shipment, como la estructura es la misma, usamos la del dominio, pero no lo veo tan correcto.
    // Podría haberse creado un record interno igual, para tenerlos separados.

    // No podemos usar la clase Product del paquete product porque ese Product es el de nuestro dominio.
    // Además, la estructura de este Product es ligeramente distinta, solo para la respuesta al cliente.
    public record Product(String id,
                          String name,
                          double unitPrice,
                          int quantity) {
    }

    // Igual con la parte de Invoice. Usaremos una distinta para la respuesta, ya que la que tenemos en el
    // paquete invoice es la del dominio.
    // PriceSummary si lo podemos usar porque está en el paquete common, lo que indica que es usable en
    // distintos modelos.
    // paymentDue solo aparece si es una factura impagada, por eso es opcional.
    // Incluimos la anotación Jackson para que no aparezca en la respuesta si es null.
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public record InvoiceDetails(String invoiceId,
                                 String paymentStatus,
                                 PriceSummary priceSummary,
                                 Optional<LocalDate> paymentDue) {
    }
}
