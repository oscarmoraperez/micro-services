package org.oka.microservices.microsender.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.oka.microservices.microsender.configuration.RabbitMQConfiguration;
import org.oka.microservices.microsender.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void sendNotification(final Notification notification) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.NOTIFICATION_QUEUE, objectMapper.writeValueAsString(notification));

        log.info("Sending notification: " + notification);
    }
}
