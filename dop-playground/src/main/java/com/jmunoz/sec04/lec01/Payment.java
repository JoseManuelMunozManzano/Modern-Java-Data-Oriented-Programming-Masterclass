package com.jmunoz.sec04.lec01;

public sealed interface Payment {

    // Tenemos que añadir las subclases en el package sec04.lec01.
    // Vamos a añadir aquí mismo, como parte del body de esta interface, las posibles opciones de Payment.
    // Haciéndolo así (que no es que sea mejor o peor) no hace falta indicar en la interface la palabra clave permits,
    // ya que se asume automáticamente que CreditCard y Paypal son las opciones que permite Payment.
    record CreditCard(String number,
                      String cvv) implements Payment {
    }

    record Paypal(String email) implements Payment {
    }
}
