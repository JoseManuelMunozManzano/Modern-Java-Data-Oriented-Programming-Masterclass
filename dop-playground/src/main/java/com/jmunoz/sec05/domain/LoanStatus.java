package com.jmunoz.sec05.domain;

public sealed interface LoanStatus {

    // Cualquier LoanStatus tendrá Loan, así que lo creamos como méto-do abstracto
    Loan loan();

    record Submitted(Loan loan) implements LoanStatus{
    }

    record Reviewed(Loan loan) implements LoanStatus{
    }

    record Approved(Loan loan,
                    double interestRate) implements LoanStatus{
    }

    record Denied(Loan loan,
                  String reason) implements LoanStatus{
    }
}
