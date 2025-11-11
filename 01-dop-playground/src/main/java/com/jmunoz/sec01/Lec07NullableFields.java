package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/*
    Los records son portadores de datos, y sus campos pueden ser null.
    Si necesitamos evitar que sean null, usaremos un compact constructor para validar los campos,
    o usaremos Optional para hacerlo más explícito.
*/
public class Lec07NullableFields {

    private static final Logger log = LoggerFactory.getLogger(Lec07NullableFields.class);

    // name no puede ser nulo. Esto lo validamos usando un compact constructor.
    //
    // nickname es un campo opcional. Si no queremos devolver null, podemos hacerlo un campo Optional<String>,
    // haciendo explícito su comportamiento. Pero hay un problema con este enfoque (ver abajo).
    record Person(String name,
                  Optional<String> nickName) {

        // Un compact constructor simplifica la llamada y evita un esfuerzo por parte del llamador, que
        // no tiene que pasarme un Optional.empty() (ver sam y jake)
        Person(String name) {
            this(name, Optional.empty());
        }

        // Usando un constructor non-canonical evito que los que me llaman tengan que mandar un Optional.
        // Lo creo yo (ver william y jessie)
        Person(String name, String nickname) {
            this(name, Optional.of(nickname));
        }
    }

    static void main() {
        // Problema con el enfoque de hacer Optional un campo de un Record: Tenemos que pasar un Optional<String>,
        // y yo, como llamador tengo que hacer un trabajo extra.
        // Aunque en una llamada a un méto-do se considera mala praxis aceptar un Optional (debería poder aceptar
        // el objeto y dentro del méto-do asignarlo a un Optional), en una llamada a un Record no se considera
        // tan mala práctica, ¿por qué? Porque no hay otra opción (salvo quizá la que vimos en la clase anterior
        // de usar un méto-do nuevo, que tenemos que conocer y llamar).
        var sam = new Person("sam", Optional.empty());

        // Hemos aliviado el esfuerzo por parte del llamador usando un compact constructor.
        var jake = new Person("jake");

        // En este caso si pasamos el nickname, pero, de nuevo, es una molestia que tengamos que pasar
        // un Optional.
        var william = new Person("william", Optional.of("bill"));

        // Este problema lo podemos evitar creando un constructor non-canonical.
        var jessie = new Person("jessie", "jess");

        log.info("{}", jake);
        log.info("{}", jessie);
    }
}
