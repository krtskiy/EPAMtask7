package com.epam.rd.java.basic.practice7.constants;

/**
 * Holds entities declared in XSD document.
 *
 * @author D.Koretskyi on 03.09.2020.
 */
public enum XML {
    BANK("Bank"),
    DEPOSIT("Deposit"),
    NAME("Name"),
    COUNTRY("Country"),
    DEPOSITOR("Depositor"),
    ACCOUNT_ID("AccountId"),
    TYPE("Type"),
    AMOUNT_ON_DEPOSIT("AmountOnDeposit"),
    PROFITABILITY("Profitability"),
    TIME_CONSTRAINTS("TimeConstraints");

    private String value;

    XML(String value) {
        this.value = value;
    }

    /**
     * Determines if a name is equal to the string value wrapped by this enum element.<br/>
     * If a SAX/StAX parser make all names of elements and attributes interned you can use
     * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
     * @param name string to compare with value.
     * @return value.equals(name)
     */
    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }

}
