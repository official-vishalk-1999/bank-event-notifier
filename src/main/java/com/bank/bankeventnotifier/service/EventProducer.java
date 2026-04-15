package com.bank.bankeventnotifier.service;

import com.bank.bankeventnotifier.model.TransactionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    private final RabbitTemplate rabbitTemplate;

    public EventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(TransactionEvent event) {

        System.out.println("Sending transaction: " + event.getTransactionId());

        rabbitTemplate.convertAndSend("bank-exchange", "routing.notification", event);
        rabbitTemplate.convertAndSend("bank-exchange", "routing.fraud", event);
    }
}