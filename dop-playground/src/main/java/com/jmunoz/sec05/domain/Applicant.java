package com.jmunoz.sec05.domain;

// Ignoramos validaciones por simplicidad, ya que se han discutido anteriormente.
public record Applicant(String name,
                        int creditScore,
                        int income) {
}
