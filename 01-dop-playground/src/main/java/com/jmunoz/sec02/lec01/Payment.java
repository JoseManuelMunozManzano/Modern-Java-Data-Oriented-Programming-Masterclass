package com.jmunoz.sec02.lec01;

public sealed abstract class Payment permits Cash, CreditCard {

    public abstract void process(int amount);

}
