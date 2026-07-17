package com.bank.bankeventnotifier.consumer;

import com.bank.bankeventnotifier.config.RabbitMQConfig;
import com.bank.bankeventnotifier.model.TransactionEvent;
import com.bank.bankeventnotifier.store.ResultStore;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final ResultStore resultStore;

    public NotificationConsumer(ResultStore resultStore) {
        this.resultStore = resultStore;
    }

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void receive(TransactionEvent event) {
        resultStore.save(event.getTransactionId(), "notification",
                "Notification sent for " + event.getTransactionId());
    }
}
