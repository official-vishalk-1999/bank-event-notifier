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

    public static final String TRANSACTION_KEY = "route.transaction";

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
        return BindingBuilder.bind(notificationQueue()).to(exchange()).with(TRANSACTION_KEY);
    }

    @Bean
    public Binding fraudBinding() {
        return BindingBuilder.bind(fraudQueue()).to(exchange()).with(TRANSACTION_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter());
        return template;
    }
}
