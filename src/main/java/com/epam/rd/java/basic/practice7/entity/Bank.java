package com.epam.rd.java.basic.practice7.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Root container. Implements the Bank entity.
 *
 * @author D.Koretskyi on 02.09.2020.
 */
public class Bank {

    private List<Deposit> deposits;

    public List<Deposit> getDeposits() {
        if (deposits == null) {
            deposits = new ArrayList<>();
        }
        return deposits;
    }

    @Override
    public String toString() {
        if (deposits == null || deposits.isEmpty()) {
            return "There are no deposits!";
        }
        StringBuilder result = new StringBuilder();
        for (Deposit deposit : deposits) {
            result.append(deposit).append('\n');
        }
        return result.toString();
    }
}
