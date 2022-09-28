package org.oka.microservices.microrecipient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.oka.microservices.microrecipient.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static org.oka.microservices.microrecipient.configuration.RabbitMQConfiguration.NOTIFICATION_QUEUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = {NOTIFICATION_QUEUE})
    public void consume(String notification) {
        Notification notificationObj = objectMapper.readValue(notification, Notification.class);
        log.info("Notification received: " + notificationObj);

        notificationService.persistNotification(notificationObj);
    }
}
