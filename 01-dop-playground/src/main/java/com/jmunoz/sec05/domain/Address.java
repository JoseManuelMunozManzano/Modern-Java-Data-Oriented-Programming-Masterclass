package com.jmunoz.sec05.domain;

// Ignoramos validaciones por simplicidad, ya que se han discutido anteriormente.
public record Address(String street,
                      String city,
                      String zipCode) {
}
