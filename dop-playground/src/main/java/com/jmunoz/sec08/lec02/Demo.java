package com.jmunoz.sec08.lec02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    static void main() throws JsonProcessingException {

        // Esta es la respuesta que obtendríamos de una llamada a un servicio remoto.
        // Indicamos en el JSON la property type (ver ContactType para saber por qué y los valores que podemos indicar)
        var email = """
                {"info": "sam@gmail.com", "type": "email"}
                """;

        var phone = """
                {"info": "+1-123-456-7890", "type": "phone"}
                """;

        log.info("{}", deserialize(email));
        log.info("{}", deserialize(phone));

        // En este caso Jackson no sabe como deserializar. Tira por la defaultImpl de la anotación.
        log.info("{}", deserialize("{}"));
    }

    // Este méto-do sirve para ver el problema.
    // IMPORTANTE: Hay que comentar las anotaciones de ContactType para ver el problema.
    // Al ejecutar vemos el error: abstract types either need to be mapped to concrete types, have custom deserializer.
    // Cuando usamos tipos abstractos, Jackson no sabe qué subtipo usar si no le damos pistas (las anotaciones).
    private static ContactType deserialize(String json) throws JsonProcessingException {
        return mapper.readValue(json, ContactType.class);
    }
}
