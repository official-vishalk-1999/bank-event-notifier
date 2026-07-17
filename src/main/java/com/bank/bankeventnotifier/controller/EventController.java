package com.bank.bankeventnotifier.controller;

import com.bank.bankeventnotifier.model.TransactionEvent;
import com.bank.bankeventnotifier.producer.EventProducer;
import com.bank.bankeventnotifier.store.ResultStore;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EventController {

    private final EventProducer producer;
    private final ResultStore resultStore;

    public EventController(EventProducer producer, ResultStore resultStore) {
        this.producer = producer;
        this.resultStore = resultStore;
    }

    @PostMapping("/event")
    public String send(@RequestBody TransactionEvent event) {
        producer.publish(event);
        return event.getTransactionId();
    }

    @GetMapping("/result/{id}")
    public Map<String, String> result(@PathVariable String id) {
        String notification = resultStore.get(id, "notification");
        String fraud = resultStore.get(id, "fraud");
        return Map.of(
            "notification", notification == null ? "" : notification,
            "fraud", fraud == null ? "" : fraud
        );
    }
}
