package org.oka.microservices.microrecipient.configuration;

import org.oka.microservices.microrecipient.service.NotificationConsumer;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {
    public static final String EXCHANGE_NOTIFICATION = "exchange_notification";
    public static final String NOTIFICATION_QUEUE = "notification";

    @Bean
    Queue notificationQueu() {
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

    @Bean
    SimpleMessageListenerContainer notificationListener(ConnectionFactory connectionFactory,
                                                        MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(NOTIFICATION_QUEUE);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    MessageListenerAdapter notificationListenerAdapter(NotificationConsumer consumer) {
        return new MessageListenerAdapter(consumer, "consume");
    }
}
