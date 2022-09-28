package org.oka.microservices.microvisualizer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oka.microservices.microvisualizer.model.Notification;
import org.oka.microservices.microvisualizer.model.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        List<Notification> result = new ArrayList<>();
        this.notificationRepository.findAll().forEach(result::add);

        return result;
    }
}
