package com.jmunoz.sec04.lec03;

public class Demo {

    static void main() {

        // Validamos por adelantado. Evitamos sorpresas posteriores.
        // Para ver como se cumplen las validaciones, indicar samgmail.com en vez de sam@gmail.com
        var eMailAddress = new EMailAddress("sam@gmail.com");
        var message = new Message("Hi Sam, How are you?");
        EMailService.send(eMailAddress, message);
    }
}
