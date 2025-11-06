package com.jmunoz.sec05.domain;

public interface LoanProcessor {

    // Este méto-do actúa como un engine para hacer transiciones entre estados definidos en LoanStatus.
    // Nuestros estados finales son o bien Approved o Denied, por eso este méto-do se vuelve a llamar en Submitted y Reviewed.
    default LoanStatus process(LoanStatus loanStatus) {
        return switch (loanStatus) {
            case LoanStatus.Submitted submitted -> this.process(this.handle(submitted));
            case LoanStatus.Reviewed reviewed -> this.process(this.handle(reviewed));
            case LoanStatus.Approved approved -> approved;
            case LoanStatus.Denied denied -> denied;
        };
    }

    LoanStatus handle(LoanStatus.Submitted submitted);
    LoanStatus handle(LoanStatus.Reviewed reviewed);

}
