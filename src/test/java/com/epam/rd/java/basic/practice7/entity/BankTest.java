package com.epam.rd.java.basic.practice7.entity;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author D.Koretskyi on 04.09.2020.
 */
public class BankTest {

    @Test
    public void toStringMethodShouldWorkProperlyWithEmptyBank() {
        String expected = "There are no deposits!" + System.lineSeparator();

        Bank bank = new Bank();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        System.out.println(bank);
        Assert.assertEquals(expected, outContent.toString());
    }

    @Test
    public void toStringMethodShouldWorkProperlyWithNotEmptyBank() {
        String expected = "Депозит в банке ПриветБанк, в стране Нарния, "
                + "владелец депозита - Дуся Корецушкин с ID 69. "
                + "Депозит типа Металлический на сумму 420.0, "
                + "длительностью в 13.0 месяцев, с процентной ставкой 66.0%.\n";

        Bank bank = new Bank();
        Deposit deposit = new Deposit();
        deposit.setBankName("ПриветБанк");
        deposit.setCountry("Нарния");
        deposit.setDepositorName("Дуся Корецушкин");
        deposit.setDepositorId(69);
        deposit.setDepositType("Металлический");
        deposit.setAmountOnDeposit(420.0);
        deposit.setProfitability(0.66);
        deposit.setTimeConstraints(13.0);
        bank.getDeposits().add(deposit);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        System.out.print(bank);
        Assert.assertEquals(expected, outContent.toString());
    }

}