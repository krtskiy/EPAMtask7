package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;

public final class Main {

    public static void main(final String[] args) {
        Bank bank = new Bank();
        Deposit deposit = new Deposit();
        bank.getDeposits();
        bank.toString();

    }
}
