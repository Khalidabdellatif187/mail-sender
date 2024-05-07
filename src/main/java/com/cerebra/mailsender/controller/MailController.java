package com.cerebra.mailsender.controller;

import com.cerebra.mailsender.clients.EventMailClient;
import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final MailMapper mailMapper;
    private final EventMailClient eventMailClient;
    @Value("${domain.name}")
    private String domainName;


    @GetMapping
    public ResponseEntity<List<MailDto>> getAllMails(){
        return new ResponseEntity<List<MailDto>>(mailService.getAllMails(),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MailDto mailDto){
        return new ResponseEntity<>(mailService.saveMail(mailMapper.unMap(mailDto)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MailDto> getById(@PathVariable("id")Long id) throws JsonProcessingException {
        Mail mail = mailService.getById(id);
        return new ResponseEntity<>(mailMapper.map(mail),HttpStatus.OK);
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable("id")Long id) throws MessagingException, JsonProcessingException {
        return new ResponseEntity<>(mailService.sendEmail(id),HttpStatus.OK);
    }


    @GetMapping("/events")
    public ResponseEntity<JsonNode> getEvents(@RequestParam("messageId")String messageId) throws JsonProcessingException {
        String jsonData = eventMailClient.getMailgunEvents(domainName,messageId);
        JsonNode jsonNode = new ObjectMapper().readTree(jsonData);
        return new ResponseEntity<>(jsonNode,HttpStatus.OK);
    }





}
