package org.oka.microservices.microsender.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {
    public static final String EXCHANGE_NOTIFICATION = "exchange_notification";
    public static final String NOTIFICATION_QUEUE = "notification";

    @Bean
    Queue notification() {
        return new Queue(NOTIFICATION_QUEUE, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NOTIFICATION);
    }

    @Bean
    public Declarables directExchangeBindings(DirectExchange exchangeEvent, Queue notification) {
        return new Declarables(
                BindingBuilder.bind(notification).to(exchangeEvent).with(EXCHANGE_NOTIFICATION)
        );
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, Jackson2ObjectMapperBuilder builder) {
        return new RabbitTemplate(connectionFactory);
    }
}
