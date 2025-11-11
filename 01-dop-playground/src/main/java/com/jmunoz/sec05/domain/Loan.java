package com.jmunoz.sec05.domain;

public sealed interface Loan {

    // Como application es común a todas las implementaciones, la creamos aquí como méto-do abstracto.
    // No es obligatorio crearla aquí.
    LoanApplication application();

    record PersonalLoan(LoanApplication application) implements Loan {
    }

    record PropertyLoan(LoanApplication application,
                        Property property) implements Loan {
    }

    record AutoLoan(LoanApplication application,
                    Vehicle vehicle) implements Loan {
    }
}
