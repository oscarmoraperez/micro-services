package org.oka.microservices.microvisualizer.controller;

import lombok.RequiredArgsConstructor;
import org.oka.microservices.microvisualizer.model.Notification;
import org.oka.microservices.microvisualizer.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class MicrovisualizerController {
    private final NotificationService notificationService;

    @GetMapping(value = "/notification")
    public ResponseEntity<List<Notification>> notification() {

        return new ResponseEntity<>(notificationService.getAllNotifications(), OK);
    }
}