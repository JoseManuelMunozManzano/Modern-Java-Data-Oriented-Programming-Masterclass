package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

// Non-canonical constructor es un constructor con una firma diferente a la de los componentes del record.
public class Lec04NonCanonicalConstructor {

    private static final Logger log = LoggerFactory.getLogger(Lec04NonCanonicalConstructor.class);

    record Task(String title,
                LocalDate createdAt) {

        // Non-canonical constructor: Indicamos un valor por defecto a createdAt y así no tenemos que enviar un
        // valor para crear una instancia.
        // El funcionamiento es el siguiente: Tenemos que delegar la construcción de la instancia al canonical constructor,
        // pasando la data que nos envían más la por defecto que pasamos nosotros.
        // Pero aquí no inicializamos nada.
        Task(String title) {
            this(title, LocalDate.now());
        }
    }

    static void main() {
        // Problema: actualmente, tenemos que indicar el campo createdAt. Podría ser un campo opcional,
        // con un valor por defecto el día de hoy.
        // Usamos non-canonical constructor para no tener que enviarlo.
        var task = new Task("Submit report", LocalDate.now());
        log.info("{}", task);

        // Usando non-canonical constructor ya no necesito pasar el segundo argumento.
        var task2 = new Task("Submit report 2");
        log.info("{}", task2);
    }
}
