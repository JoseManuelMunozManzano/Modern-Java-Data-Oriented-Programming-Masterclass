package com.jmunoz.sec07.lec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Evitamos tener que indicar Result. Solo usaremos Success y Failure.
import com.jmunoz.sec07.lec02.Result.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    static void main() {
        // Leemos la data sin problemas.
        readFile(Path.of("myfile1.txt"));

        // Acceso Denegado.
        readFile(Path.of("myfile2.txt"));

        // Fichero no encontrado
        readFile(Path.of("myfile3.txt"));

        // Ejemplo de llamada a servicio externo que devuelve un resultado que modelamos con Result<T>
        callExternalService("http://goolge.com");
        callExternalService("http://sdafhasdhjklfsd.com");
    }

    // Esto con fines demostrativos.
    private static void readFile(Path path) {
        switch (FileReader.readFile(path)) {
            case Success(String data) -> log.info("data: {}", data);
            // Usamos record deconstruct y pattern matching para los componentes del record.
            // Gracias a eso en vez de throwable podemos indicar AccessDeniedException y IOException.
            case Failure(AccessDeniedException e) -> log.error("denied: {}", e.getMessage());
            case Failure(IOException e) -> log.error("io error: {}", e.getMessage());
            // Para cualquier otra excepción.
            case Failure(Throwable e) -> log.error("error: {}", e.getMessage());
        };
    }

    private static void callExternalService(String url) {
        log.info("{}", ExternalServiceClient.call(url));
    }
}
