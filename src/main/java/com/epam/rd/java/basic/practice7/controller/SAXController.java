package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.XML;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author D.Koretskyi on 03.09.2020.
 */
public class SAXController extends DefaultHandler {

    private String xmlFileName;
    private String currentElement;
    private Bank bank;
    private Deposit deposit;

    private static SAXParserFactory factory = SAXParserFactory.newInstance();
    private static final Logger logger = Logger.getLogger("SAXController".getClass().getName());

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() {
        factory.setNamespaceAware(true);

        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.severe(e.getMessage());
        }
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentElement = localName;

        if (XML.BANK.equalsTo(currentElement)) {
            bank = new Bank();
            return;
        }

        if (XML.DEPOSIT.equalsTo(currentElement)) {
            deposit = new Deposit();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        }

        if (XML.NAME.equalsTo(currentElement)) {
            deposit.setBankName(elementText);
            return;
        }

        if (XML.COUNTRY.equalsTo(currentElement)) {
            deposit.setCountry(elementText);
            return;
        }

        if (XML.DEPOSITOR.equalsTo(currentElement)) {
            deposit.setDepositorName(elementText);
            return;
        }

        if (XML.ACCOUNT_ID.equalsTo(currentElement)) {
            deposit.setDepositorId(Integer.parseInt(elementText));
            return;
        }

        if (XML.TYPE.equalsTo(currentElement)) {
            deposit.setDepositType(elementText);
            return;
        }

        if (XML.AMOUNT_ON_DEPOSIT.equalsTo(currentElement)) {
            deposit.setAmountOnDeposit(Double.parseDouble(elementText));
            return;
        }

        if (XML.PROFITABILITY.equalsTo(currentElement)) {
            deposit.setProfitability(Double.parseDouble(elementText));
            return;
        }

        if (XML.TIME_CONSTRAINTS.equalsTo(currentElement)) {
            deposit.setTimeConstraints(Double.parseDouble(elementText));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (XML.DEPOSIT.equalsTo(localName)) {
            bank.getDeposits().add(deposit);
        }
    }

}
