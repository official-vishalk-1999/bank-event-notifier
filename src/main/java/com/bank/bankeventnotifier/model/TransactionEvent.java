package com.bank.bankeventnotifier.model;

import java.io.Serializable;

public class TransactionEvent implements Serializable {

    private String transactionId;
    private String cardNumber;
    private String expiry;
    private String cvv;
    private double amount;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}