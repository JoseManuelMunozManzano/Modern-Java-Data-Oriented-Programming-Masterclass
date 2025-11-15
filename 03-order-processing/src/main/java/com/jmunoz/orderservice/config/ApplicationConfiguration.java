package com.jmunoz.orderservice.config;

import com.jmunoz.orderservice.client.*;
import com.jmunoz.orderservice.client.impl.*;
import com.jmunoz.orderservice.orchestrator.OrderOrchestrator;
import com.jmunoz.orderservice.orchestrator.impl.OrderOrchestratorImpl;
import com.jmunoz.orderservice.service.*;
import com.jmunoz.orderservice.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);

    // Inyectado por el constructor.
    private final RestClient.Builder builder;

    // Cuando obtengamos el builder, vamos a añadir el request interceptor,
    // ya que builder puede tener una lista de interceptors.
    public ApplicationConfiguration(RestClient.Builder builder) {
        this.builder = builder.requestInterceptor(new LoggingInterceptor());
    }

    // Creando los beans de los clientes.
    @Bean
    public ProductClient productClient(@Value("${product.service.url}") String baseUrl) {
        return new ProductServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public CustomerClient customerClient(@Value("${customer.service.url}") String baseUrl) {
        return new CustomerServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public CouponClient couponClient(@Value("${coupon.service.url}") String baseUrl) {
        return new CouponServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public PaymentClient paymentClient(@Value("${payment.service.url}") String baseUrl) {
        return new PaymentServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public BillingClient billingClient(@Value("${billing.service.url}") String baseUrl) {
        return new BillingServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public ShippingClient shippingClient(@Value("${shipping.service.url}") String baseUrl) {
        return new ShippingServiceClient(buildRestClient(baseUrl));
    }

    // Creando los beans de los servicios.
    @Bean
    public RequestValidatorService requestValidatorService(ProductClient productClient, CustomerClient customerClient, CouponClient couponClient) {
        return new RequestValidatorServiceImpl(productClient, customerClient, couponClient);
    }

    @Bean
    public PriceCalculator priceCalculator() {
        return new PriceCalculatorImpl();
    }

    @Bean
    public PaymentBillingService paymentBillingService(PaymentClient paymentClient, BillingClient billingClient) {
        return new PaymentBillingServiceImpl(paymentClient, billingClient);
    }

    @Bean
    public ShippingService shippingService(ShippingClient shippingClient) {
        return new ShippingServiceImpl(shippingClient);
    }

    // Creando el bean para el orchestrator.
    @Bean
    public OrderOrchestrator orderOrchestrator(RequestValidatorService validatorService,
                                               PriceCalculator priceCalculator,
                                               PaymentBillingService paymentBillingService,
                                               ShippingService shippingService) {
        return new OrderOrchestratorImpl(
                validatorService,
                priceCalculator,
                paymentBillingService,
                shippingService
        );
    }

    // Creando el bean de OrderService.
    @Bean
    public OrderService orderService(OrderOrchestrator orderOrchestrator) {
        return new OrderServiceImpl(orderOrchestrator);
    }

    private RestClient buildRestClient(String baseUrl) {
        log.info("base url: {}", baseUrl);
        return this.builder.baseUrl(baseUrl)
                .build();
    }
}
