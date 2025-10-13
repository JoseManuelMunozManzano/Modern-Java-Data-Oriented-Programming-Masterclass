package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// ¿Son los records inmutables? ¡Depende!
public class Lec05ImmutableRecord {

    private static final Logger log = LoggerFactory.getLogger(Lec05ImmutableRecord.class);

    record Team(String name,
                List<String> members) {

        // Este problema de la no inmutabilidad (ver abajo) de una lista,
        // se corrige (realmente no) haciendo copia defensiva en un compact constructor.
        // Al obtener la lista de items via constructor, copiaremos la data en una nueva lista.
        //
        // Como esta lista es distinta (distinta posición de memoria) a la que se usa abajo para modificar la data,
        // los nuevos elementos no entran a esta lista y por eso es inmutable.
        //
        // Pero sigue sin ser 100% inmutable. Esto funciona en este caso porque es una lista de String, y String
        // es inmutable. Si la lista contiene objetos mutables, entonces se puede seguir mutando.
        //
        // Comentar y volver a ejecutar.
        Team {
            members = List.copyOf(members);
        }
    }

    static void main() {

        var members = new ArrayList<String>();
        members.add("sam");
        members.add("mike");

        var team = new Team("dev team", members);

        log.info("{}", team);

        // Se pueden modificar los elementos de una lista, porque verdaderamente no cambia la posición de memoria
        // de la lista. Es decir, un record que contenga un componente lista, será mutable.
        // Para hacerlo inmutable, usamos el compact constructor de arriba (comentarlo y volver a ejecutar)
        members.add("jake");
        log.info("{}", team);

        // Pero no es 100% inmutable.
        // Si esta instrucción devolviera un objeto mutable en vez de un String (que es inmutable), podríamos
        // usar el méto-do setter para modificar el objeto.
        //
        //team.members().getFirst().set...
    }
}
