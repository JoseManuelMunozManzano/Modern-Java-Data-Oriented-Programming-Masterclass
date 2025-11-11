package com.jmunoz.sec06.lec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    // Imaginamos que estos dos records vienen de una librería de terceros, no sealed.
    // Se devuelve o el tipo Phone o el tipo EMail (nunca los dos a la vez).
    record Phone(String number) {}
    record EMail(String address) {}

    static void main() {
        log.info("left: {}", getContactMethod(1));
        log.info("right: {}", getContactMethod(2));

        // Como usaríamos Either en la vida real.
        switch (getContactMethod(2)) {
            case Either.Left(Phone phone) -> log.info("sending message to number {}", phone.number());
            case Either.Right(EMail eMail) -> log.info("sending message to email {}", eMail.address());
        };
    }

    // Para el id 1 obtendremos Phone y, para cualquier otro id, obtendremos EMail.
    private static Either<Phone, EMail> getContactMethod(int id) {
        return id == 1 ? Either.left(new Phone("123-456-7890")) : Either.right(new EMail("sam@gmail.com"));
    }
}
