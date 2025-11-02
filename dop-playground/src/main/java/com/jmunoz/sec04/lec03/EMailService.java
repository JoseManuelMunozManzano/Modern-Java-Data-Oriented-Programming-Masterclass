package com.jmunoz.sec04.lec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// EMail debe tener un formato válido.
// Message debe tener un mínimo de 10 caracteres y un máximo de 5000 caracteres.
public class EMailService {

    private static final Logger log = LoggerFactory.getLogger(EMailService.class);

    // ¿Qué pasa si el llamador intercambia los parámetros?
    // ¿Es un email válido?
    // ¿Es válido el mensaje?
    public static void send(String emailAddress, String message) {
        log.info("sending {} to {}", message, emailAddress);
    }

    // Siguiendo DOP.
    // Imposible intercambiar los parámetros.
    // Sabemos que EMailAddress es válido, pero no hacemos validaciones en este méto-do. Confiamos en el record EMailAddress.
    // Sabemos que Message es válido, pero no hacemos validaciones en este méto-do. Confiamos en el record Message.
    // Modelamos la data como data, sin comportamientos.
    // Las validaciones se han hecho en los límites.
    // La data es inmutable.
    public static void send(EMailAddress eMailAddress, Message message) {
        log.info("sending {} to {}", message.value(), eMailAddress.value());
    }
}
