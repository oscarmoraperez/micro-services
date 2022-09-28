package org.oka.microservices.microsender.controller;

import lombok.RequiredArgsConstructor;
import org.oka.microservices.microsender.model.Notification;
import org.oka.microservices.microsender.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class MicrosenderController {
    private final NotificationService notificationService;

    @PostMapping(value = "/notification")
    public ResponseEntity<Void> notification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);

        return new ResponseEntity<>(OK);
    }
}
