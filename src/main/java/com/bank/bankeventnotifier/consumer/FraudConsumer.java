package com.bank.bankeventnotifier.consumer;

import com.bank.bankeventnotifier.model.TransactionEvent;
import com.bank.bankeventnotifier.service.ProcessingResultStore;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FraudConsumer {

    private final ProcessingResultStore store;

    public FraudConsumer(ProcessingResultStore store) {
        this.store = store;
    }

    @RabbitListener(queues = "fraud-queue")
    public void receive(TransactionEvent event) {

        String msg = "Fraud check completed for txn " + event.getTransactionId();

        System.out.println(msg);

        store.addResult(event.getTransactionId(), "fraud", msg);
    }
}