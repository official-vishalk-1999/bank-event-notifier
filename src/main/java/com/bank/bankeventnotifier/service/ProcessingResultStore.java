package com.bank.bankeventnotifier.service;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProcessingResultStore {

    private final ConcurrentHashMap<String, String> results = new ConcurrentHashMap<>();

    public void addResult(String txnId, String service, String message) {
        results.put(txnId + "-" + service, message);
    }

    public String getNotification(String txnId) {
        return results.get(txnId + "-notification");
    }

    public String getFraud(String txnId) {
        return results.get(txnId + "-fraud");
    }
}