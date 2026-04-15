package com.bank.bankeventnotifier.consumer;

import com.bank.bankeventnotifier.model.TransactionEvent;
import com.bank.bankeventnotifier.service.ProcessingResultStore;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final ProcessingResultStore store;

    public NotificationConsumer(ProcessingResultStore store) {
        this.store = store;
    }

    @RabbitListener(queues = "notification-queue")
    public void receive(TransactionEvent event) {

        String msg = "Email sent for txn " + event.getTransactionId()
                + " amount " + event.getAmount();

        System.out.println(msg);

        store.addResult(event.getTransactionId(), "notification", msg);
    }
}