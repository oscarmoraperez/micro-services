package org.oka.microservices.microcollector.controller.service;

import org.oka.microservices.microcollector.model.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "microrecipient", url = "${micro-recipient.url}")
public interface MicrorecipientClient {

    @RequestMapping(method = RequestMethod.GET, value = "/notification")
    List<Notification> getNotifications();
}
