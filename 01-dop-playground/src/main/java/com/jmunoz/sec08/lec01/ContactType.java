package com.jmunoz.sec08.lec01;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Comentar estas anotaciones para ver el problema en Demo.java (Jackson no sabe deserializar)
// En la anotación @JsonSubtypes tenemos que indicar todos los posibles subtypes.
// En la anotación @JsonTypeInfo tenemos que decirle a Jackson como encontrar el subtype correcto.
//   Id.DEDUCTION indica que la estructura de los subtypes es diferente, así que Jackson mirará el JSON y los campos.
//   Cuando Jackson vea un JSON con address irá por EMail, y si ve countryCode y number tirará por el tipo Phone.
//   Podemos indicar (no es obligatorio) una implementación por defecto en caso de que Jackson no encuentre el subtipo.
//      En este caso, si Jackson no sabe que subtipo es, por defecto deserializa al subtipo EMail.
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = ContactType.EMail.class)
@JsonSubTypes({
        @JsonSubTypes.Type(ContactType.EMail.class),
        @JsonSubTypes.Type(ContactType.Phone.class)
})
public sealed interface ContactType {

    record EMail(String address) implements ContactType {
    }

    record Phone(String countryCode,
                 String number) implements ContactType {
    }
}
