package com.jmunoz.sec08.lec03;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Las anotaciones que estaban en ContactType.java las traemos a esta clase dummy.
// Es una clase donde tenemos la configuración Jackson de nuestro dominio (ContactType.java) para la deserialización.
// El nombre de esta clase puede ser cualquiera.
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = ContactType.EMail.class)
@JsonSubTypes({
        @JsonSubTypes.Type(ContactType.EMail.class),
        @JsonSubTypes.Type(ContactType.Phone.class)
})
public class ContactTypeMixIn {
}
