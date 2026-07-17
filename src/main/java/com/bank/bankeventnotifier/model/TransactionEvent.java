package com.bank.bankeventnotifier.model;

public class TransactionEvent {

    private String transactionId;
    private double amount;

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
