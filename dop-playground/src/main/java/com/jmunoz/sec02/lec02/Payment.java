package com.jmunoz.sec02.lec02;

public sealed interface Payment permits CreditCard, Paypal {

    void process(int amount);

}
