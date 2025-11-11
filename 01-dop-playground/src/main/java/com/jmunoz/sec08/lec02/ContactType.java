package com.jmunoz.sec08.lec02;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// En este ejemplo, la estructura JSON es la misma tanto para EMail como para Phone.
// Jackson no puede detectar el tipo automáticamente, así que tenemos que indicarle cierta información.
// El campo type se va a usar para mapear al tipo apropiado, pero lo que realmente necesitamos es el campo info.
//
// En la anotación @JsonTypeInfo usaremos Id.NAME y en include indicaremos PROPERTY, que indica que en el JSON
// tiene que haber una propiedad que vamos a usar para detectar el tipo. En property indicamos el nombre de esa
// propiedad. Basado en la property type vamos a detectar el subtipo correcto.
//
// Al indicar property, en la anotación @JsonSubTypes tenemos que indicar en name lo que tiene que buscar en
// la property type. Tiene que buscar "email" y entonces mapeará a tipo EMail, o "phone"
// y entonces mapeará a tipo "Phone".
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = ContactType.EMail.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContactType.EMail.class, name = "email"),
        @JsonSubTypes.Type(value = ContactType.Phone.class, name = "phone")
})
public sealed interface ContactType {

    record EMail(String info) implements ContactType {
    }

    record Phone(String info) implements ContactType {
    }
}
