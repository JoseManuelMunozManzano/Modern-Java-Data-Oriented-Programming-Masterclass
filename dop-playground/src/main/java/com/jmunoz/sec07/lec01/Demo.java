package com.jmunoz.sec07.lec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Evitamos tener que indicar FileReadResponse. Solo usaremos Data, FileNotFound y AccessDenied
import com.jmunoz.sec07.lec01.FileReadResponse.*;

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
    }

    // Esto con fines demostrativos.
    // Es más limpio que el tradicional bloque try-catch con todas las excepciones.
    private static void readFile(Path path) {
        switch (FileReader.readFile(path)) {
            case Data data -> log.info("data: {}", data.content());
            case AccessDenied denied -> log.error("denied: {}", denied.message());
            case FileNotFound fileNotFound -> log.error("not found: {}", fileNotFound.message());
        };
    }
}
