package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

// Los métodos de acceso a records se pueden sobreescribir sin cambiar la firma de dichos métodos.
public class Lec06AccessorMethodOverride {

    private static final Logger log = LoggerFactory.getLogger(Lec06AccessorMethodOverride.class);

    // Cuando creamos un record con sus componentes, por defecto crea métodos getter para cada componente.
    // Esos son los Accessor Methods. Y se pueden sobreescribir, pero no deberíamos cambiar la firma del méto-do.
    record Person(String firstName,
                  String lastName) {

        // Este es el getter que el compilador generará por defecto, pero le hemos añadido que
        // se transforme a mayúsculas. No es muy eficiente, pero no estamos hablando de eso, sino
        // de que puede sobreescribirse su funcionamiento.
        public String lastName() {
            return this.lastName.toUpperCase();
        }

        // Imaginemos que firstName puede ser null y, cuando intentamos acceder a person.firstName, puede
        // devolver null.
        // Pero no queremos devolver null sino Optional.
        // En ese caso, podríamos hacer esto.
        // Pero falla porque estamos cambiando la firma del méto-do, que se supone debe devolver un String.
        //
        // public Optional<String> firstName() {
        //    return Optional.ofNullable(this.firstName);
        // }

        // Lo que podríamos hacer en este caso es introducir nuevos métodos que formen parte del record.
         public Optional<String> firstNameOptional() {
            return Optional.ofNullable(this.firstName);
         }
    }

    static void main() {
        var person = new Person("john", "doe");

        // El apellido se transforma a mayúsculas si invocamos un Accessor Method.
        log.info("{}", person.lastName());

        // Pero si usamos el objeto, el apellido no estará en mayúsculas.
        log.info("{}", person);

        // Usando el nuevo méto-do firstNameOptional
        person.firstNameOptional()
                .orElse("Pepe");
    }
}
