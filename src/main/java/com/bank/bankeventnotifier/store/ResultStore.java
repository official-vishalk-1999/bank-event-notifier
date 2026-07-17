package com.bank.bankeventnotifier.store;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ResultStore {

    private final ConcurrentHashMap<String, String> results = new ConcurrentHashMap<>();

    public void save(String transactionId, String service, String message) {
        results.put(transactionId + "-" + service, message);
    }

    public String get(String transactionId, String service) {
        return results.get(transactionId + "-" + service);
    }
}
