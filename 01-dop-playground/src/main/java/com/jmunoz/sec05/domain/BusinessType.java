package com.jmunoz.sec05.domain;

// Es un enum y no un sealed type porque no tiene ninguna propiedad ni ninguna información
// específica de Retail u Office.
public enum BusinessType {
    RETAIL,
    OFFICE;
}
