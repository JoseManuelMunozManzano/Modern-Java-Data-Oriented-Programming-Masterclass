package com.jmunoz.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Lec03TypePattern {

    private static final Logger log = LoggerFactory.getLogger(Lec03TypePattern.class);

    static void main() {
        log.info("{}", isEmpty(null));
        log.info("{}", isEmpty(""));
        log.info("{}", isEmpty("jomuma"));
        log.info("{}", isEmpty(List.of()));
        log.info("{}", isEmpty(List.of(1)));
        log.info("{}", isEmpty(Map.of()));
        log.info("{}", isEmpty(Map.of(1, 2)));
        log.info("{}", isEmpty(new Object[]{}));
        log.info("{}", isEmpty(new Object[]{1, 2}));
    }

    // Código muy legible, si lo comparamos con Lec01InstanceOf.
    private static boolean isEmpty(Object object) {
        log.info("received: {}", object);
        return switch (object) {
            case null -> true;
            // string solo puede usarse en este case
            case String string -> string.isEmpty();
            case Collection<?> collection -> collection.isEmpty();
            case Map<?, ?> map -> map.isEmpty();
            case Object[] array -> array.length == 0;
            default -> false;
        };
    }
}
