package com.bank.bankeventnotifier.consumer;

import com.bank.bankeventnotifier.config.RabbitMQConfig;
import com.bank.bankeventnotifier.model.TransactionEvent;
import com.bank.bankeventnotifier.store.ResultStore;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FraudConsumer {

    private final ResultStore resultStore;

    public FraudConsumer(ResultStore resultStore) {
        this.resultStore = resultStore;
    }

    @RabbitListener(queues = RabbitMQConfig.FRAUD_QUEUE)
    public void receive(TransactionEvent event) {
        resultStore.save(event.getTransactionId(), "fraud",
                "Fraud check done for " + event.getTransactionId());
    }
}
