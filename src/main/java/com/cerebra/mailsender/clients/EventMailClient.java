package com.cerebra.mailsender.clients;


import com.cerebra.mailsender.configuration.MailGunEventsBasicAuthSecurity;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="event-client",url="${mailgun.event.client}",configuration = MailGunEventsBasicAuthSecurity.class)
public interface EventMailClient {

    @GetMapping("{domainName}/events")
    JsonNode getMailgunEvents(@PathVariable("domainName")String domainName, @RequestParam("message-id") String messageId);
}
