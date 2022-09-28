package org.oka.microservices.microcollector.controller.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MicrocollectorService {
    private final MicrorecipientClient microrecipientClient;

    @Scheduled(fixedDelay = 5000)
    public void getAllNotifications() {

        log.info("Notifications retrieved: " + microrecipientClient.getNotifications());
    }
}
