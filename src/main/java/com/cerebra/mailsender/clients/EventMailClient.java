package com.cerebra.mailsender.clients;


import com.cerebra.mailsender.configuration.MailGunEventsBasicAuthSecurity;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="event-client",url="${mailgun.event.client}",configuration = MailGunEventsBasicAuthSecurity.class)
public interface EventMailClient {

    @GetMapping("{domainName}/events")
    String getMailgunEvents(@PathVariable("domainName")String domainName,
                            @RequestParam(value = "message-id" , required = true) String messageId);



    @GetMapping("{domainName}/events")
    String getClickedLinkEvents(@PathVariable("domainName")String domainName,
                                @RequestParam("message-id") String messageId,
                                @RequestParam("event")String event);



}
