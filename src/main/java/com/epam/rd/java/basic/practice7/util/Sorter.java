package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;

import java.util.Collections;
import java.util.Comparator;

/**
 * @author D.Koretskyi on 03.09.2020.
 */
public class Sorter {

    /**
     * Sorts deposits by depositors id.
     */
    public static final Comparator<Deposit> SORT_DEPOSITS_BY_DEPOSITORS_ID = Comparator.comparingInt(Deposit::getDepositorId);

    /**
     * Sorts deposits by amount of the money.
     */
    public static final Comparator<Deposit> SORT_DEPOSITS_BY_AMOUNT_OF_MONEY = Comparator.comparingDouble(Deposit::getAmountOnDeposit);

    /**
     * Sorts deposits by duration of the deposit.
     */
    public static final Comparator<Deposit> SORT_DEPOSITS_BY_TIME_CONSTRAINTS = Comparator.comparingDouble(Deposit::getTimeConstraints);

    /**
     * Sorts deposits by profitability.
     */
    public static final Comparator<Deposit> SORT_DEPOSITS_BY_PROFITABILITY = Comparator.comparingDouble(Deposit::getProfitability);

    public static final void sortDepositsByDepositorsId(Bank bank) {
        Collections.sort(bank.getDeposits(), SORT_DEPOSITS_BY_DEPOSITORS_ID);
    }

    public static final void sortDepositsByAmountOfMoney(Bank bank) {
        Collections.sort(bank.getDeposits(), SORT_DEPOSITS_BY_AMOUNT_OF_MONEY);
    }

    public static final void sortDepositsByProfitability(Bank bank) {
        Collections.sort(bank.getDeposits(), SORT_DEPOSITS_BY_PROFITABILITY);
    }
    public static final void sortDepositsByTimeConstraints(Bank bank) {
        Collections.sort(bank.getDeposits(), SORT_DEPOSITS_BY_TIME_CONSTRAINTS);
    }

    public static void main(String[] args) {
        DOMController domController = new DOMController(Constants.VALID_XML_FILE);
        domController.parse(true);
        Bank bank = domController.getBank();

        System.out.println("~~~~~~~~~~ Default order ~~~~~~~~~~");
        System.out.println(bank);

        System.out.println("~~~~~~~~~~ By ID ~~~~~~~~~~");
        Sorter.sortDepositsByDepositorsId(bank);
        System.out.println(bank);

        System.out.println("~~~~~~~~~~ By profitability ~~~~~~~~~~");
        Sorter.sortDepositsByProfitability(bank);
        System.out.println(bank);


    }


}
