package com.bank.bankeventnotifier.controller;

import com.bank.bankeventnotifier.service.ProcessingResultStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResultController {

    private final ProcessingResultStore store;

    public ResultController(ProcessingResultStore store) {
        this.store = store;
    }

    @GetMapping("/result/{id}")
    public String getResult(@PathVariable String id) {

        String notification = store.getNotification(id);
        String fraud = store.getFraud(id);

        return "Notification: " + notification + "\nFraud: " + fraud;
    }
}