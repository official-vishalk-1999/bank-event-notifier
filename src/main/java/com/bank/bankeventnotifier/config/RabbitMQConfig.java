package com.bank.bankeventnotifier.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "bank-exchange";
    public static final String NOTIFICATION_QUEUE = "notification-queue";
    public static final String FRAUD_QUEUE = "fraud-queue";

    public static final String ROUTING_KEY_NOTIFICATION = "routing.notification";
    public static final String ROUTING_KEY_FRAUD = "routing.fraud";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Queue fraudQueue() {
        return new Queue(FRAUD_QUEUE);
    }

    @Bean
    public Binding notificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(exchange())
                .with(ROUTING_KEY_NOTIFICATION);
    }

    @Bean
    public Binding fraudBinding() {
        return BindingBuilder
                .bind(fraudQueue())
                .to(exchange())
                .with(ROUTING_KEY_FRAUD);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}