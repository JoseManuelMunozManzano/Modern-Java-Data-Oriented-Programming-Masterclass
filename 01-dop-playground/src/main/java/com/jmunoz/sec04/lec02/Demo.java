package com.jmunoz.sec04.lec02;

public class Demo {

    static void main() {
        // El usuario se loguea con credenciales válidas. Como parte de MFA, enviamos un código.

        var sam = new User("sam", new ContactType.Phone("+1", "123-456-7890"));
        var mike = new User("mike", new ContactType.EMail("mike@gmail.com"));

        LoginVerificationService.sendVerificationCode(sam);
        LoginVerificationService.sendVerificationCode(mike);
    }
}
