# Modern Java: Data Oriented Programming Masterclass

Del curso de Udemy: https://www.udemy.com/course/java-data-oriented-programming/

## Introduction

Java siempre ha sido un lenguaje orientado a objetos. Es un enfoque de programación donde la data y las acciones (comportamiento) que operan sobre esa data están agrupados en objetos.

Este enfoque funciona realmente bien cuando estamos construyendo aplicaciones monolíticas donde diferentes módulos hablan entre ellos pasándose objetos que incluyen tanto data como lógica.

![alt Monolith Applications](./images/01-MonolithApplication.png)

Pero los tiempos han cambiado. Hoy, en arquitectura basada en microservicios, ese enfoque orientado a objetos tradicional puede sentirse limitante.

![alt Modern Application](./images/02-ModernApplication.png)

Miremos este ejemplo: Imaginemos que tenemos dos servicios:

![alt Microservices Communication](./images/03-MicroservicesCommunication.png)

El servicio de la izquierda, `order service` quiere obtener el detalle de los productos del servicio de la derecha, `product service`.

En el modelo tradicional, `product service` devuelve la respuesta exitosa `product` o respuesta con error (quizás 404 - product not found). Esta comunicación tradicional es de respuestas fijas, muy rígida.

Necesitamos respuestas ricas y contextuales, pero en la programación tradicional los servicios están normalmente diseñados solo para decir si o no. Aquí es donde entra `data oriented programming`.

**Data Oriented Programming**

- No se trata solo de estilo de codificación.
- Se trata de modelar claramente la data, expresando intención con precisión, y haciendo la comunicación flexible e informativa.

Los microservicios pueden hablar como lo hacemos los humanos en vez de respuestas rígidas de tipo si o no.

![alt Microservices Communication with Data Oriented Programming](./images/04-MicroservicesCommunicationWithDataOrientedProgramming.png)

Por ejemplo, las distintas bolas representan estas respuestas:

- `producto discontinuado. Estas son las alternativas`.
- `El producto ya no está en stock, pero viene en dos días`.
- `Si, tengo el producto`.

Ya no se trata solo de comunicación entre microservicios, sino que podemos usar estos conceptos para diseñar flujos de trabajo empresariales complejos dentro de una aplicación.

Esto lo podremos ver en una aplicación con Spring Boot que vamos a construir más adelante para ver todo lo que se ha aprendido en el curso.

**What Will You Learn?**

- Comprender los principios de `Data Oriented Programming` (DOP) y como remodela el diseño de aplicaciones modernas.
- Modelar dominios de negocio usando estructuras de datos claras, expresivas, impulsadas por la intención.
- Mejorar la comunicación entre servicios a través de tipos de datos bien definidos y con significado.
- Manejar la incertidumbre y el fallo con más matices que las típicas respuestas rígidas basadas en success/failure.
- Adoptar un cambio de mentalidad. De código que reacciona a código que comunica.

`Data Oriented Programming` puede ayudarnos a desarrollar microservicios que sean fáciles de leer y de mantener, pero para temas de rendimiento y de escalabilidad, aprenderemos en otro curso sobre `Virtual Threads`.

**Syllabus**

- Records
- Sealed Types
- Pattern Matching
- Data Oriented Programming
- Domain Modeling
- Handling Uncertainty
- Error Handling
- DOP: Deserialization Challenges in Microservices
- Application Development

Los tres primeros apartados (Records, Sealed Types y Pattern Matching) son los bloques de construcción básicos para `Data Oriented Programming`.

## Records

[README](./01-dop-playground/README.md#records)

Ver proyecto `01-dop-playground`, paquete `sec01`:

- `Lec01RecordBasicsDemo`: Creamos un record dentro de esta clase y exploramos lo básico de los records, como getters, equals, toString...
- `Lec02CanonicalConstructor`: Ejemplo del constructor autogenerado por defecto, llamado Canonical Constructor y el problema que conlleva si el record tiene muchos campos.
- `Lec03CompactConstructor`: Ejemplo con CompactConstructor, que arregla el problema de construir Canonical Constructor.
  - Añadimos validaciones.
- `Lec04NonCanonicalConstructor`: Ejemplo para crear firmas diferentes a la de los componentes del record.
- `Lec05ImmutableRecord`: Ejemplos de inmutabilidad (no lo es al 100%) de los records.
- `Lec06AccessorMethodOverride`: Ejemplos de sobre-escritura del funcionamiento de los métodos getter de los `Records`.
- `Lec07NullableFields`: Vemos diferentes formas de tratar con campos de un `record` que pueden ser nulos.
- `Lec08StaticMembers`: Ejemplos de miembros estáticos y métodos de factoría estáticos.
- `Lec09RecordInterface`: Ejemplo de un `record` implementando una interface.
- `Lec10RecordReflection`: Ejemplos de uso de reflexión.

## Sealed Types

[README](./01-dop-playground/README.md#sealed-types)

Ver proyecto `01-dop-playground`, paquete `sec02`:

- `lec01`
    - `Payment`: Clase abstracta.
    - `Cash`: Clase sealed que es permitida que extienda de `Payment` y permite que `CashRewards` la extienda.
    - `CashRewards`: Clase final que es permitida que extienda de `Cash`.
    - `CreditCard`: Clase final que es permitida que extienda de `Payment`.
    - `Demo`: Clase que utiliza nuestra jerarquía de clases creadas anteriormente.

- `lec02`
    - `Payment`: Interface que usa `sealed`.
    - `CreditCard`: Records que es permitido que implemente de `Payment`.
    - `Paypal`: Records que es permitido que implemente de `Payment`.
    - `Demo`: Clase que utiliza nuestra jerarquía de clases creadas anteriormente.

## Pattern Matching

[README](./01-dop-playground/README.md#pattern-matching)

Ver proyecto `01-dop-playground`, paquete `sec03`:

- `Lec01InstanceOf`: Ejemplo de uso de `instanceof` usando `pattern variable`.
- `Lec02SwitchExpression`: Ejemplo de uso de `switch expression`.
- `Lec03TypePattern`: Ejemplo de uso de `switch expression` con `type pattern`. Queda un código muy legible si lo comparamos con `Lec01InstanceOf`.
- `Lec04PatternLabelDominance`: Vemos que `case` se ejecuta cuando más de uno cumple la condición.
- `Lec05GuardedPattern`: Vemos como funciona una cláusula guarda en un `pattern label`.
- `Lec06UnnamedVariable`: Vemos como trabajar con `pattern matching` cuando solo nos interesa el tipo, pero no necesitamos el valor.
- `Lec07RecordPattern`: Vemos un ejemplo de `record pattern`.
- `Lec08NestedRecordPattern`: Vemos un ejemplo de `record pattern` anidados.
- `Lec09Exhaustiveness`: Vemos que no es necesario indicar el caso `default` si se cubren todos los posibles valores de entrada.

## Principles Of Data Oriented Programming

[README](./01-dop-playground/README.md#principles-of-data-oriented-programming)

Ver proyecto `01-dop-playground`, paquete `sec04`:

- `lec01`
    - `Payment`: Es un `sealed interface` que contiene internamente dos `records` con los tipos de pago permitidos.
    - `Demo`: Clase principal.
- `lec02`
    - `ContactType`: Es un `sealed interface` que contiene internamente dos `records` con los tipos de contacto permitidos.
    - `User`: Es un `record` con un campo de tipo `ContactType`.
    - `LoginVerificationService`: Clase que, en función del tipo de contacto, envía un código de login de una u otra forma.
    - `Demo`: Clase principal.
- `lec03`
    - `EMailService`:
        - Desarrollo usando primitivos/cadenas en vez de tipos pequeños, pensando que así es más fácil el desarrollo.
        - Desarrollo usando records `EMailAddress` y `Message`.
    - `EMailAddress`: Record que modela un email válido.
    - `Message`: Record que modela un mensaje válido.
    - `Demo`: Clase principal.

## Domain Modeling

[README](./01-dop-playground/README.md#domain-modeling)

Ver proyecto `01-dop-playground`, paquete `sec05`:

- `domain`
    - `Applicant`: Record
    - `LoanTerms`: Record
    - `LoanApplication`: Record que contiene Applicant y LoanTerms
    - `Address`: Record
    - `Property`: Es una interface Sealed con dos types (records), Residential y Commercial
    - `BusinessType`: Es un enum y no un sealed type porque no tiene ninguna propiedad
    - `Vehicle`: Es una interface Sealed con dos types, Car y Motorcycle
    - `Loan`: Es una interface Sealed con tres types, PersonalLoan, PropertyLoan y AutoLoan
    - `LoanStatus`: Es una interface Sealed con los types, Submitted, Reviewed, Approved y Denied
    - `LoanProcessor`: Es una interface que modela el comportamiento de LoanStatus (no es Sealed)
- `impl`
    - `LoanProcessorImpl`: Implementación de la interface LoanProcessor.
- `Demo`: Clase main para hacer las pruebas.

## Modeling Uncertainty With Types

[README](./01-dop-playground/README.md#modeling-uncertainty-with-types)

Ver proyecto `01-dop-playground`, paquete `sec06`:

- `lec01`
    - `Option`: Es un `sealed interface`, genérico.
        - Internamente los `record` que contiene son `Present<T>` y `Absent<T>`
        - Tiene también métodos estáticos.
    - `Demo`: Clase main.
- `lec02`
    - `Either`: Es un `sealed interface`, genérico.
        - Internamente los `record` que contiene son `Left<L, R>` y `Right<L, R>`.
        - Tiene también métodos estáticos helper.
    - `Demo`: Clase main para hacer pruebas.

## Error Handling

[README](./01-dop-playground/README.md#error-handling)

Ver proyecto `01-dop-playground`, paquete `sec07`:

- `lec01`
    - `FileReadResponse`: Es un `sealed interface` que usaremos para modelar tres posibles resultados.
        - Creamos los `record` siguientes: `Data`, `FileNotFound` y `AccessDenied`.
    - `FileReader`: Clase utility que intenta leer un fichero y devuelve `FileReadResponse` (lee data o excepción).
    - `Demo`: Clase main para hacer pruebas.
- En `dop-playground`
    - `myfile1.txt`: Es un fichero de texto con el que jugar para hacer pruebas.
    - `myfile2.txt`: Es un fichero de texto con el que jugar para hacer pruebas. Simularemos el error acceso denegado.
        - Copiar `myfile1.txt`
        - Ejecutamos `chmod 000 myfile2.txt`
- `lec02`
    - `Result`: Es un `sealed interface`.
        - Internamente, tiene los `record` siguientes: `Success` y `Failure`.
        - Creamos métodos helper estáticos.
    - `FileReader`: Usamos el tipo genérico `Result`.
    - `Demo`: Clase main para hacer pruebas.
    - `ExternalServiceClient`: Simula la llamada a un servicio externo que devuelve una respuesta que modelamos usando `Result`.

## Polymorphic Deserialization

[README](./01-dop-playground/README.md#polymorphic-deserialization)

Ver proyecto `01-dop-playground`, paquete `sec08`:

- `lec01`
    - `ContactType`: Es un `sealed interface` que contiene los siguientes `records`: `EMail` y `Phone`.
    - `Demo`: Clase main para hacer pruebas.
- `lec02`
    - `ContactType`: Es un `sealed interface` que contiene los siguientes `records`: `EMail` y `Phone`.
    - `Demo`: Clase main para hacer pruebas.
- `lec03`
    - `ContactType`: Es un `sealed interface` que contiene los siguientes `records`: `EMail` y `Phone`.
    - `ContactTypeMixIn`: Nuestra clase `mixin` que contiene la configuración de ContactType para deserialización.
    - `Demo`: Clase main para hacer pruebas.

## Application Development - Order Workflow Processing - Phase 1

En esta sección vamos a desarrollar una aplicación usando Spring Boot.

La documentación general del proyecto está en `02-order-processing-workflow` y el desarrollo del proyecto se encuentra en `03-order-processing`.

Las distintas fases emulan distintos requerimientos a lo largo del tiempo.

Requerimientos: [README](./02-order-processing-workflow/01-order-processing-system-phase-1.md)

[README](./03-order-processing/README.md#phase-1)

Los paquetes y clases creados para este proyecto han sido:

- `model`
    - Todas estas clases son los modelos necesarios para hacer las peticiones y obtener las respuestas de los servicios externos.
        - `product`
            - `Product`: Es un `sealed interface` que modela los `records` siguientes: `Single` y `Bundle`.
                - Es la respuesta del servicio `product`.
            - `ProductStatus`: Es un `sealed interface` que modela los `records` siguientes: `Active` y `Discontinued`.
                - Es la respuesta del servicio `product`.
        - `common`
            - `Address`: Es un `record`.
                - También podría haber estado en el sub-paquete `customer`, pero como es posible que se use en otras clases, lo dejamos en `common`.
            - `PriceSummary`: Es un `record`.
                - También podría haber estado en el sub-paquete `invoice`, pero como es posible que se use en otras clases, lo dejamos en `common`.
        - `customer`
            - `Customer`: Es un `sealed interface` que modela los `records` siguientes: `Regular` y `Business`.
                - Es la respuesta del servicio `customer`.
        - `payment`
            - `PaymentRequest`: Es un `record` que modela la petición que haremos al servicio `payment`.
            - `PaymentStatus`: Es un `sealed interface` que modela los `records` siguientes: `Processed` y `Declined`.
                - Es la respuesta del servicio `payment`.
        - `invoice`
            - `InvoiceRequest`: Es un `sealed interface` que modela los `records` siguientes: `Paid` y `Unpaid`.
                - Enviamos este request para generar la factura.
            - `Invoice`: Es un `sealed interface` que modela los `records` siguientes: `Paid` y `Unpaid`.
                - Es la respuesta del servicio externo.
        - `shipping`
            - `Recipient`: Es un `record`.
            - `ShipmentItem`: Es un `record`.
            - `ShippingRequest`: Es un `record` que modela la petición que haremos al servicio `shipping`.
            - `TrackingDetails`: Es un `record`.
            - `Shipment`: Es un `record`.
            - `ShippingResponse`: Es un `record` que modela la respuesta que obtendremos del servicio `shipping`.
    - Todas estas clases son los modelos necesarios para hacer las peticiones y obtener las respuestas de nuestro servicio. Estos modelos tienen validaciones y hacemos diferentes modelos (DTO y modelos que pasan al orchestrator)
    - En los DTO lo que nos importa es la serialización, deserialización y validaciones de las peticiones, y no queremos mezclar estas responsabilidades con los modelos que llegan al orchestrator.
        - `order`
            - `OrderRequest`: Es un `record` con la petición que nos hacen desde el cliente a nuestro servicio.
                - Con validaciones. Es un DTO
            - `CreateOrderCommand`: Es un `record` con la data de la orden que llega al orchestrator desde el DTO.
            - `OrderItem`: Es un `record` para el orchestrator.
            - `Order`: Es un `record` para el orchestrator.
            - `OrderResponse`: Es un `record` con la respuesta que devolvemos al cliente. Contiene los `record` internos `Product` y `InvoiceDetails`.
- `exception`
    - `ApplicationError`: Es un `sealed interface` que permite las `sealed interafaces` siguientes: `DomainError` y `SystemError`.
    - `DomainError`: Es un `sealed interface` con los `records` siguientes: `EntityNotFound`, `ProductDiscontinued` y `PaymentDeclined`.
    - `SystemError`: Es un `sealed interface` con el `record` siguiente: `RemoteServiceError`.
    - `ApplicationException`: Es el `RuntimeException`.
    - `ApplicationExceptions`: Es una utility class con muchos métodos `factory` estáticos cuya misión es lanzar las excepciones.
- `client`
    - `ProductClient`: Es una interface que modela el comportamiento.
    - `CustomerClient`: Es una interface que modela el comportamiento.
    - `PaymentClient`: Es una interface que modela el comportamiento.
    - `BillingClient`: Es una interface que modela el comportamiento.
    - `ShippingClient`: Es una interface que modela el comportamiento.
    -  `impl`
        - `AbstractServiceClient`: Es una clase abstracta que gestiona el manejo de excepciones (en vez de hacerlo en `ProductServiceClient` y los demás).
        - `ProductServiceClient`: Implementación de `ProductClient`.
        - `CustomerServiceClient`: Implementación de `CustomerClient`.
        - `PaymentServiceClient`: Implementación de `PaymentClient`.
        - `BillingServiceClient`: Implementación de `BillingClient`.
        - `ShippingServiceClient`: Implementación de `ShippingClient`.
- `service`
    - `RequestValidatorService`: Es una interface que modela el comportamiento. Validará si obtiene la data correcta de los clientes `Product` y `Client` y creará objeto Order.
    - `PriceCalculator`: Es una interface que modela el comportamiento. Dado el objeto `Order` calculará el precio.
    - `PaymentBillingService`: Es una interface que modela el comportamiento. Realiza el pago y crea la factura.
    - `ShippingService`: Es una interface que modela el comportamiento. Realiza el envío.
    - `OrderService`: Es una interface que modela el comportamiento. Recibe OrderRequest y devolverá OrderResponse.
    - `impl`
        - `RequestValidatorServiceImpl`: Implementación de `RequestValidatorService`.
        - `PriceCalculatorImpl`: Implementación de `PriceCalculator`.
        - `PaymentBillingServiceImpl`: Implementación de `PaymentBillingService`.
        - `ShippingServiceImpl`: Implementación de `ShippingService`.
        - `OrderServiceImpl`: Implementación de la interface `OrderService`.
- `orchestrator`
    - `OrderState`: Es un `sealed interface` que define los `records` siguientes: `Placed`, `Validated`, `Priced`, `Invoiced`, `Shipped` y `Fulfilled`.
    - `OrderOrchestrator`: Es una interface que modela el comportamiento para realizar la transición entre los distintos estados.
    - `impl`
        - `OrderOrchestratorImpl`: Implementación de la interface `OrderOrchestrator`.
- `util`
    - `DomainDtoMapper`: Hace mapeos de OrderRequest a CreateOrderCommand y también mapeamos a OrderResponse a partir de Order, Invoice y List<Shipment>
- `controller`
    - `OrderController`: El controlador de nuestra aplicación.
    - `advice`
        - `ApplicationExceptionHandler`: Es el Controller Advice.
- `config`
    - `LoggingInterceptor`: Para poder hacer debug fácilmente vamos a hacer log en cada request que se envíe.
    - `ApplicationConfiguration`: Clase de configuración de beans de Spring.
        - Se añade a RestClient `LoggingInterceptor`.
    - `CustomerMixIn`: Clase de configuración para poder hacer deserialización polimórfica.
    - `ProductMixIn`: Clase de configuración para poder hacer deserialización polimórfica.
    - `ProductStatusMixIn`: Clase de configuración para poder hacer deserialización polimórfica.
    - `InvoiceMixIn`: Clase de configuración para poder hacer deserialización polimórfica.
- `application.properties`
```sql
product.service.url=http://localhost:7070/products
customer.service.url=http://localhost:7070/customers
payment.service.url=http://localhost:7070/payment
billing.service.url=http://localhost:7070/billing
shipping.service.url=http://localhost:7070/shipping
```

## Application Development - Order Workflow Processing - Phase 2

Vamos a introducir algunos requerimientos en nuestro servicio.

En concreto, para esta fase 2, vamos a añadir el soporte para códigos de cupones.

Requerimientos: [README](./02-order-processing-workflow/02-order-processing-system-phase-2.md)

[README](./03-order-processing/README.md#phase-2)

Los paquetes y clases creados/modificados para este proyecto han sido:

- `model`
    - `coupon`
        - `Coupon`: Es un `sealed interface` que modela los `records` siguientes: `Flat`, `Percentage` y `None`.
            - Es la respuesta del servicio `coupon`.
    - `order`:
        - `Order`: Modificamos este `record` para añadir el cupón.
        - `CreateOrderCommand`: Lo modificamos para añadir el código del cupón, que, recordemos, puede estar o no (usamos Optional).
        - `OrderRequest`: Modificamos este `dto` para añadir el código del cupón.
- `util`
    - `DomainDtoMapper`: Lo modificamos para corregir el error que da ahora por faltar el código del cupón.
- `client`
    - `CouponClient`: Es una interface que modela el comportamiento.
    -  `impl`
        - `CouponServiceClient`: Implementación de `CouponClient`.
- `service`
    - `impl`
        - `RequestValidatorServiceImpl`: Corregimos el método `validate()` inyectando `CouponClient` y añadiendo el cupón al objeto `Order`.
        - `PriceCalculatorImpl`: Lo modificamos para aplicar los descuentos del cupón.
- `config`
    - `ApplicationConfiguration`: Creamos el bean `CouponClient` y corregimos donde sea necesario añadir el cupón.
    - `CouponMixIn`: Nueva clase de configuración para poder hacer deserialización polimórfica.
- `application.properties`
```sql
coupon.service.url=http://localhost:7070/coupons
```

## Application Development - Order Workflow Processing - Phase 3

En esta fase, vamos a corregir un error que ocurre al hacer el test de `Postman` siguiente:

- `07-regular-user-single-product`: Camino feliz para un pedido regular de un solo producto.

Requerimientos: [README](./02-order-processing-workflow/03-order-processing-system-phase-3.md)

[README](./03-order-processing/README.md#phase-3)

Los paquetes y clases creados/modificados para este proyecto han sido:

- `model`
    - `shipping`
        - `ShippingStatus`: Es un nuevo `sealed interface` que modela los `records` siguientes: `Scheduled` y `Declined`.
            - Con esto, no sobraría el modelo `ShippingResponse`, pero por ahora lo dejamos.
    - `payment`
        - `RefundRequest`: Es un nuevo `record` que modela la petición de una devolucion.
- `exception`
    - `DomainError`: Modificamos esta clase para modelar `ShippingDecline` como un error de dominio.
    - `ApplicationExceptions`: Modificamos para añadir un nuevo método helper `declinedShipping(...)`.
- `client`
    - `PaymentClient`: Añadimos el método `refund(...)` para devolver el dinero.
    - `BillingClient`: Añadimos el método `cancelInvoice(...)` para cancelar la factura.
    - `ShippingClient`: Modificamos el método `schedule(...)` para devolver `ShippingStatus` en vez de `ShippingResponse`.
    - `impl`
        - `PaymentServiceClient`: Añadimos la implementación del método `refund(...)`.
        - `BillingServiceClient`: Añadimos la implementación del método `cancelInvoice(...)`.
        - `ShippingServiceClient`: Modificamos la implementación del método `schedule(...)`.
- `service`
    - `PaymentBillingService`: Añadimos el método `refundPayment(...)` para devolver el dinero.
    - `ShippingService`: Modificamos el método `scheduleShipping(...)` para devolver `ShippingStatus` en vez de `ShippingResponse`.
    - `impl`
        - `PaymentBillingService`: Añadimos la implementación del método `refundPayment(...)`.
        - `ShippingServiceImpl`: Modificamos la implementación del método `scheduleShipping(...)`.
- `orchestrator`
    - `impl`
        - `OrderOrchestratorImpl`: Corregimos el error del método `public OrderState handle(OrderState.Invoiced invoiced)`.
- `controller`
    - `advice`
        - `ApplicationExceptionHandler`: Añadimos un nuevo método `toProblemDetail(...)` para `ShippingDeclined` y lo añadimos al switch del método `handleException(...)`.

## Persistence

[README](./04-sealed-persistence/README.md)

**1 Table For All Subclasses**

Ver proyecto `04-sealed-persistence`, paquete `sec01`:

- `application`: Es el paquete donde tendremos nuestros modelos, clases de servicio, haremos las transiciones entre estados
    - `model`
        - `Payment`: Es una `sealed interface` que contiene los `records` siguientes: `CreditCard` y `Paypal`.
    - `util`
        - `DomainEntityMapper`: Hacemos el mapeo desde el modelo de dominio a la entidad y al revés.
    - `service`
        - `PaymentService`: La clase de servicio.
- `persistence`: Es el paquete donde tendremos entidades y repositorios.
    - `entity`
        - `PaymentEntity`: La representación en Java de la tabla `payment` de BD.
        - `PaymentType`: Es un `enum` con las mismas posibilidades que ofrece el model `Payment`, es decir `CREDIT_CARD` y `PAYPAL`.
    - `repository`
        - `PaymentRepository`: El repositorio.

En `test/java/com/jmunoz/sealedpersistence` creamos el test siguiente:

- `OneTableApproachTest`: No es realmente un test, es más lanzar una ejecución de la aplicación.

En `application.properties` añadimos, para ver las sentencias SQL:

```
spring.jpa.show-sql=true
```

**1 Table Per Subclass**

Ver proyecto `04-sealed-persistence`, paquete `sec02`:

- `application`: Es el paquete donde tendremos nuestros modelos, clases de servicio, haremos las transiciones entre estados
    - `model`
        - `AccountType`: Es una `sealed interface` que contiene los `records` siguientes: `Checking` y `Savings`.
    - `util`
        - `DomainEntityMapper`: Hacemos el mapeo desde el modelo de dominio a la entidad y al revés.
    - `service`
        - `AccountService`: La clase de servicio.
- `persistence`: Es el paquete donde tendremos entidades y repositorios.
    - `entity`
        - `Account`: Clase abstracta con los campos en común de `CheckingAccount` y `SavingsAccount`.
        - `CheckingAccount`: La representación en Java de la tabla `checking_account` de BD.
        - `SavingsAccount`: La representación en Java de la tabla `savings_account` de BD.
    - `repository`
        - `AccountRepository`: El repositorio.

En `test/java/com/jmunoz/sealedpersistence` creamos el test siguiente:

- `TablePerClassApproachTest`: No es realmente un test, es más lanzar una ejecución de la aplicación.
