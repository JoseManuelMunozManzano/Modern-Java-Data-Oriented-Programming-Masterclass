package com.jmunoz.sec07.lec01;

/**
 * - Modelamos estos resultados:
 *   - El fichero existe y podemos obtener la data.
 *   - El fichero no se encuentra.
 *   - El fichero existe, pero se deniega el acceso porque el usuario no tiene permisos de lectura.
 */
public sealed interface FileReadResponse {

    record Data(String content) implements FileReadResponse {
    }

    record FileNotFound(String message) implements FileReadResponse {
    }

    record AccessDenied(String message) implements FileReadResponse {

    }
}
