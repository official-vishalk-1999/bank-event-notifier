package com.bank.bankeventnotifier.controller;

import com.bank.bankeventnotifier.model.TransactionEvent;
import com.bank.bankeventnotifier.service.EventProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventController {

    private final EventProducer producer;

    public EventController(EventProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/event")
    public String sendEvent(@RequestBody TransactionEvent event) {
        producer.sendMessage(event);
        return event.getTransactionId();
    }
}