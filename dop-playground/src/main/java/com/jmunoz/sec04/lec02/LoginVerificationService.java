package com.jmunoz.sec04.lec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

// El escenario a simular es este:
// Cuando el usuario se loguea, dependiendo del tipo de contacto que ha configurado el usuario,
// se enviará un código de login como parte de una autenticación multi-factor.
public class LoginVerificationService {

    private static final Logger log = LoggerFactory.getLogger(LoginVerificationService.class);

    public static void sendVerificationCode(User user) {
        var code = ThreadLocalRandom.current().nextInt(1000, 10000);
        switch (user.contactType()) {
            case ContactType.Phone phone -> phoneVerification(code, user.name(), phone);
            case ContactType.EMail eMail -> emailVerification(code, user.name(), eMail);
        }
    }

    private static void emailVerification(int code, String name, ContactType.EMail eMail) {
        log.info("sending the code {} to {} via email {}", code, name, eMail.address());
    }

    private static void phoneVerification(int code, String name, ContactType.Phone phone) {
        log.info("sending the code {} to {} via phone {}-{}", code, name, phone.countryCode(), phone.number());
    }
}
