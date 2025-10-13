package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

// Los records no pueden extender clases, ¡pero pueden implementar interfaces!
public class Lec09RecordInterface {

    private static final Logger log = LoggerFactory.getLogger(Lec09RecordInterface.class);

    record EmailTemplate(String template) implements UnaryOperator<String> {

        @Override
        public String apply(String name) {
            return template.formatted(name);
        }
    }

    static void main() {
        // Imaginemos que tenemos una lista de Customers o un stream de Customers y les vamos a enviar
        // un mensaje de bienvenida.
        var emailTemplate = new EmailTemplate("Hi %s, Welcome...");

        Stream.of("sam", "mike", "jake")
                .map(emailTemplate)
                .forEach(log::info);
    }
}
