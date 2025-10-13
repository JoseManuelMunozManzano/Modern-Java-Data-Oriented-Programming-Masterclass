package com.jmunoz.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

// Hacer introspección o acceder a componentes de un record dinámicamente.
public class Lec10RecordReflection {

    private static final Logger log = LoggerFactory.getLogger(Lec10RecordReflection.class);

    record Person(String name,
                  int age) {
    }

    static void main() {

        accessRecordComponent(String.class);
        accessRecordComponent(Person.class);

        accessFieldValues("sam");
        accessFieldValues(new Person("sam", 10));
    }

    private static <T> void accessRecordComponent(Class<T> type) {
        if (!type.isRecord()) {
            log.info("{} is not a record", type);
            return;
        }

        Arrays.stream(type.getRecordComponents())
                .forEach(rc -> log.info("{}-{}", rc.getName(), rc.getType()));
    }

    private static void accessFieldValues(Object object) {
        if (!object.getClass().isRecord()) {
            log.info("{} is not a record", object.getClass());
            return;
        }

        Arrays.stream(object.getClass().getRecordComponents())
                .forEach(rc -> {
                    try {
                        log.info("{}-{}", rc.getName(), rc.getAccessor().invoke(object));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
