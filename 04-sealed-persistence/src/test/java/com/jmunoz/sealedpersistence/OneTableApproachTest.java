package com.jmunoz.sealedpersistence;

import com.jmunoz.sealedpersistence.sec01.application.model.Payment;
import com.jmunoz.sealedpersistence.sec01.application.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OneTableApproachTest {

    private static final Logger log = LoggerFactory.getLogger(OneTableApproachTest.class);

    @Autowired
    private PaymentService paymentService;

    @Test
    void entitySaveRetrieve() {

        // guardar credit card
        var creditCard = new Payment.CreditCard("123-456-789", "123");
        this.paymentService.save(creditCard);

        // guadar paypal
        var paypal = new Payment.Paypal("sam@gmail.com");
        this.paymentService.save(paypal);

        // consulta por id
        log.info("{}", this.paymentService.getPayment(1));
        log.info("{}", this.paymentService.getPayment(2));

        // obtener to-do
        this.paymentService.printAllRecords();
    }
}
