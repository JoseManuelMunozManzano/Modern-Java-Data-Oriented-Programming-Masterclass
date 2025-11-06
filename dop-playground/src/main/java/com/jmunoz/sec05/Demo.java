package com.jmunoz.sec05;

import com.jmunoz.sec05.domain.*;
import com.jmunoz.sec05.impl.LoanProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Demo {

    public static final Logger log = LoggerFactory.getLogger(Demo.class);

    static void main() {
        var service = new LoanService(new LoanProcessorImpl());

        // Cambiar el importe a 650, a 600 u otro importe para ver cuando se aprueba o se deniega el préstamo.
        // Cambiar income de 80_000 a 120_000.
        var applicant = new Applicant("sam", 700, 80_000);
        var loanTerms = new LoanTerms(60_000, 10);
        var loanApplication = new LoanApplication(applicant, loanTerms);
        var address = new Address("123 main street", "atlanta", "30005");

        var personalLoan = new Loan.PersonalLoan(loanApplication);
        var carLoan = new Loan.AutoLoan(loanApplication, new Vehicle.Car("honda", 2025));
        var motorcycleLoan = new Loan.AutoLoan(loanApplication, new Vehicle.Motorcycle("harley davidson", 600));
        var residentialLoan = new Loan.PropertyLoan(loanApplication, new Property.Residential(address, 5));
        var commercialLoan = new Loan.PropertyLoan(loanApplication, new Property.Commercial(address, BusinessType.OFFICE));

        Stream.of(personalLoan, carLoan, motorcycleLoan, residentialLoan, commercialLoan)
                .forEach(service::apply);
    }

    private static class LoanService {
        private final LoanProcessor loanProcessor;

        public LoanService(LoanProcessor loanProcessor) {
            this.loanProcessor = loanProcessor;
        }

        void apply(Loan loan) {
            var submitted = new LoanStatus.Submitted(loan);
            var loanStatus = this.loanProcessor.process(submitted);
            log.info("status: {}", loanStatus);
        }
    }
}
