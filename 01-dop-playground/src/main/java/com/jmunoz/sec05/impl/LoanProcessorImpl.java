package com.jmunoz.sec05.impl;

import com.jmunoz.sec05.domain.*;

public class LoanProcessorImpl implements LoanProcessor {

    private static final String DENY_MESSAGE_FORMAT = "Credit score must be at least %d and income must be at least %d";

    @Override
    public LoanStatus handle(LoanStatus.Submitted submitted) {
        return switch (submitted.loan()) {
            case Loan.PersonalLoan loan -> this.review(loan, 600, 50_000);
            case Loan.AutoLoan loan -> this.review(loan, 650, 60_000);
            case Loan.PropertyLoan loan -> this.review(loan, 700, 100_000);
        };
    }

    private LoanStatus review(Loan loan, int minScore, int minIncome) {
        var applicant = loan.application().applicant();
        return applicant.creditScore() >= minScore && applicant.income() >= minIncome ?
                new LoanStatus.Reviewed(loan) :
                new LoanStatus.Denied(loan, DENY_MESSAGE_FORMAT.formatted(minScore, minIncome));
    }

    @Override
    public LoanStatus handle(LoanStatus.Reviewed reviewed) {
        var loan = reviewed.loan();
        var interestRate = switch (loan) {
            case Loan.PersonalLoan _ -> 4.5;
            case Loan.AutoLoan(_, Vehicle.Motorcycle(_, var cc)) when cc < 500 -> 6.00;
            case Loan.AutoLoan(_, Vehicle.Motorcycle _) -> 7.00;
            case Loan.AutoLoan(_, Vehicle.Car _) -> 5.00;
            case Loan.PropertyLoan(_, Property.Residential(_, var rooms)) when rooms < 4 -> 6.00;
            case Loan.PropertyLoan(_, Property.Residential _) -> 6.75;
            case Loan.PropertyLoan(_, Property.Commercial _) -> 8.00;
        };

        return new LoanStatus.Approved(loan, interestRate);
    }
}
