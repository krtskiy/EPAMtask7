package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.controller.SAXController;
import com.epam.rd.java.basic.practice7.controller.StAXController;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.util.Sorter;

public final class Main {

    private static void howToUse() {
        System.out.println("You should pass only filename in main method parameters!");
    }

    public static void main(final String[] args) {
        if (args.length != 1) {
            howToUse();
            return;
        }

        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);

        ////////// DOM //////////
        DOMController domController = new DOMController(xmlFileName);
        domController.parse();
        Bank bank = domController.getBank();

        // sort by id
        Sorter.sortDepositsByDepositorsId(bank);

        DOMController.saveToXml(bank, Constants.DOM_OUTPUT_XML_FILE);
        System.out.println("DOM output ==> " + Constants.DOM_OUTPUT_XML_FILE);

        ////////// SAX //////////
        SAXController saxController = new SAXController(xmlFileName);
        saxController.parse();
        bank = saxController.getBank();

        // sort by profitability
        Sorter.sortDepositsByProfitability(bank);

        DOMController.saveToXml(bank, Constants.SAX_OUTPUT_XML_FILE);
        System.out.println("SAX output ==> " + Constants.SAX_OUTPUT_XML_FILE);

        ////////// StAX //////////
        StAXController stAXController = new StAXController(xmlFileName);
        stAXController.parse();
        bank = stAXController.getBank();

        // sort by amount of money on deposit
        Sorter.sortDepositsByAmountOfMoney(bank);

        DOMController.saveToXml(bank, Constants.STAX_OUTPUT_XML_FILE);
        System.out.println("StAX output ==> " + Constants.STAX_OUTPUT_XML_FILE);
    }
}
