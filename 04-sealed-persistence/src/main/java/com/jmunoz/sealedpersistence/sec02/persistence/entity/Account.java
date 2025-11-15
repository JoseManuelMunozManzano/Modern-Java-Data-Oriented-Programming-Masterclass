package com.jmunoz.sealedpersistence.sec02.persistence.entity;

import jakarta.persistence.*;

// No podemos usar un sealed type porque Hibernate tiene que crear proxy classes.
// Como los sealed types sirven para crear herencias restrictivas, la parte de creación de clases de Hibernate fallará.
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer accountNumber;
    protected Integer balance;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
