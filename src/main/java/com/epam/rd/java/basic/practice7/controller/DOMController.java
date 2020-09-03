package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.XML;
import com.epam.rd.java.basic.practice7.entity.Bank;
import com.epam.rd.java.basic.practice7.entity.Deposit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author D.Koretskyi on 03.09.2020.
 */
public class DOMController {

    private String xmlFileName;
    private Bank bank;

    private static final Logger logger = Logger.getLogger("DOMController".getClass().getName());

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Bank getBank() {
        return bank;
    }

    public void parse(boolean validate) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // NOSONAR
        dbf.setNamespaceAware(true);

//        if (validate) {
//            try {
//                dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
//                dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
//            } catch (ParserConfigurationException e) {
//                logger.severe(e.getMessage());
//            }
//        }

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new DefaultHandler() {
                @Override
                public void error(SAXParseException e) throws SAXException {
                    throw e;
                }
            });

            Document document = db.parse(xmlFileName);
            Element root = document.getDocumentElement();
            bank = new Bank();
            NodeList depositNodes = root.getElementsByTagName("Deposit");

            for (int i = 0; i < depositNodes.getLength(); i++) {
                Deposit deposit = getDeposit(depositNodes.item(i));
                bank.getDeposits().add(deposit);
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.severe(e.getMessage());
        }


    }

    private static Deposit getDeposit(Node item) {
        Deposit deposit = new Deposit();
        Element element = (Element) item;

        Node node = element.getElementsByTagName(XML.NAME.value()).item(0);
        deposit.setBankName(node.getTextContent());

        node = element.getElementsByTagName(XML.COUNTRY.value()).item(0);
        deposit.setCountry(node.getTextContent());

        node = element.getElementsByTagName(XML.DEPOSITOR.value()).item(0);
        deposit.setDepositorName(node.getTextContent());

        node = element.getElementsByTagName(XML.ACCOUNT_ID.value()).item(0);
        deposit.setDepositorId(Integer.parseInt(node.getTextContent()));

        node = element.getElementsByTagName(XML.TYPE.value()).item(0);
        deposit.setDepositType(node.getTextContent());

        node = element.getElementsByTagName(XML.AMOUNT_ON_DEPOSIT.value()).item(0);
        deposit.setAmountOnDeposit(Double.parseDouble(node.getTextContent()));

        node = element.getElementsByTagName(XML.PROFITABILITY.value()).item(0);
        deposit.setProfitability(Double.parseDouble(node.getTextContent()));

        node = element.getElementsByTagName(XML.TIME_CONSTRAINTS.value()).item(0);
        deposit.setTimeConstraints(Double.parseDouble(node.getTextContent()));

        return deposit;
    }

    public static Document getDocument(Bank bank) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // NOSONAR
        dbf.setNamespaceAware(true);
        Document document = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.newDocument();

            Element bElement = document.createElement(XML.BANK.value());

            document.appendChild(bElement);

            for (Deposit deposit : bank.getDeposits()) {
                Element dElement = document.createElement(XML.DEPOSIT.value());
                bElement.appendChild(dElement);

                Element element = document.createElement(XML.NAME.value());
                element.setTextContent(deposit.getBankName());
                dElement.appendChild(element);

                element = document.createElement(XML.COUNTRY.value());
                element.setTextContent(deposit.getCountry());
                dElement.appendChild(element);

                element = document.createElement(XML.DEPOSITOR.value());
                element.setTextContent(deposit.getDepositorName());
                dElement.appendChild(element);

                element = document.createElement(XML.ACCOUNT_ID.value());
                element.setTextContent("" + deposit.getDepositorId());
                dElement.appendChild(element);

                element = document.createElement(XML.TYPE.value());
                element.setTextContent(deposit.getDepositType());
                dElement.appendChild(element);

                element = document.createElement(XML.AMOUNT_ON_DEPOSIT.value());
                element.setTextContent("" + deposit.getAmountOnDeposit());
                dElement.appendChild(element);

                element = document.createElement(XML.PROFITABILITY.value());
                element.setTextContent("" + deposit.getProfitability());
                dElement.appendChild(element);

                element = document.createElement(XML.TIME_CONSTRAINTS.value());
                element.setTextContent("" + deposit.getTimeConstraints());
                dElement.appendChild(element);
            }

        } catch (ParserConfigurationException e) {
            logger.severe(e.getMessage());
        }
        return document;
    }

    public static void saveToXml(Bank bank, String xmlFileName) {
        saveToXml(getDocument(bank), xmlFileName);
    }

    public static void saveToXml(Document document, String xmlFileName) {
        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory tf = TransformerFactory.newInstance(); // NOSONAR
        try {
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(document), result);
        } catch (TransformerException e) {
            logger.severe(e.getMessage());
        }


    }

}
