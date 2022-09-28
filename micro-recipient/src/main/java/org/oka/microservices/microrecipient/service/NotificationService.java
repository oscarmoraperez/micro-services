package org.oka.microservices.microrecipient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oka.microservices.microrecipient.model.Notification;
import org.oka.microservices.microrecipient.model.NotificationRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final RabbitTemplate rabbitTemplate;

    public List<Notification> getAllNotifications() {
        List<Notification> result = new ArrayList<>();
        this.notificationRepository.findAll().forEach(result::add);

        log.info("Notifications in DB (they will be deleted): " + result);

        this.notificationRepository.deleteAll();

        return result;
    }

    public Notification persistNotification(final Notification notification) {

        log.info("Notification to be persisted: " + notification);
        return notificationRepository.save(notification);
    }
}
