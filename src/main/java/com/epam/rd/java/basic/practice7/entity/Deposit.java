package com.epam.rd.java.basic.practice7.entity;

/**
 * @author D.Koretskyi on 02.09.2020.
 */
public class Deposit {

    private String bankName;
    private String country;
    private String depositorName;
    private int depositorId;
    private String depositType;
    private double amountOnDeposit;
    private double profitability;
    private double timeConstraints;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public int getDepositorId() {
        return depositorId;
    }

    public void setDepositorId(int depositorId) {
        this.depositorId = depositorId;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public double getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(double amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public double getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(double timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public String toString() {
        return "Депозит в банке " + bankName + ", в стране " + country + ", владелец депозита - "
                + depositorName + " с ID " + depositorId + ". Депозит типа " + depositType
                + " на сумму " + amountOnDeposit + ", длительностью в " + timeConstraints
                + " месяцев, с процентной ставкой " + profitability * 100 + "%.";

    }
}
