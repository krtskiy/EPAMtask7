package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.XML;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.util.logging.Logger;

/**
 * @author D.Koretskyi on 03.09.2020.
 */
public class StAXController {

    private String xmlFileName;
    private Bank bank;

    private static final Logger logger = Logger.getLogger("StAXController".getClass().getName());

    public Bank getBank() {
        return bank;
    }

    public StAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }


    public void parse() {
        Deposit deposit = null;
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newFactory(); // NOSONAR
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        try {
            XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                    continue;
                }

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    currentElement = startElement.getName().getLocalPart();

                    if (XML.BANK.equalsTo(currentElement)) {
                        bank = new Bank();
                        continue;
                    }

                    if (XML.DEPOSIT.equalsTo(currentElement)) {
                        deposit = new Deposit();
                        continue;
                    }
                }

                if (event.isCharacters()) {
                    Characters characters = event.asCharacters();

                    if (XML.NAME.equalsTo(currentElement) && deposit != null) {
                        deposit.setBankName(characters.getData());
                        continue;
                    }

                    if (XML.COUNTRY.equalsTo(currentElement) && deposit != null) {
                        deposit.setCountry(characters.getData());
                        continue;
                    }

                    if (XML.DEPOSITOR.equalsTo(currentElement) && deposit != null) {
                        deposit.setDepositorName(characters.getData());
                        continue;
                    }

                    if (XML.ACCOUNT_ID.equalsTo(currentElement) && deposit != null) {
                        deposit.setDepositorId(Integer.parseInt(characters.getData()));
                        continue;
                    }

                    if (XML.TYPE.equalsTo(currentElement) && deposit != null) {
                        deposit.setDepositType(characters.getData());
                        continue;
                    }

                    if (XML.AMOUNT_ON_DEPOSIT.equalsTo(currentElement) && deposit != null) {
                        deposit.setAmountOnDeposit(Double.parseDouble(characters.getData()));
                        continue;
                    }

                    if (XML.PROFITABILITY.equalsTo(currentElement) && deposit != null) {
                        deposit.setProfitability(Double.parseDouble(characters.getData()));
                        continue;
                    }

                    if (XML.TIME_CONSTRAINTS.equalsTo(currentElement) && deposit != null) {
                        deposit.setTimeConstraints(Double.parseDouble(characters.getData()));
                    }
                }

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String localName = endElement.getName().getLocalPart();

                    if (XML.DEPOSIT.equalsTo(localName)) {
                        bank.getDeposits().add(deposit);
                    }
                }
            }
            reader.close();
        } catch (XMLStreamException e) {
            logger.severe(e.getMessage());
        }
    }

}
