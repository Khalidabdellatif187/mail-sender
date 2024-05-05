package com.cerebra.mailsender.controller;


import com.cerebra.mailsender.dto.MailLinkDto;
import com.cerebra.mailsender.service.MailLinkService;
import com.cerebra.mailsender.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/mail-link")
@AllArgsConstructor
public class MailLinkController {

    private final MailLinkService mailLinkService;
    private final MailService mailService;

    @GetMapping("/links")
    public ResponseEntity<List<MailLinkDto>> getAllLinks(@RequestParam("mailId")Long mailId){
        return new ResponseEntity<List<MailLinkDto>>(mailLinkService.findByMailId(mailId), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Void> count(@RequestParam("mailId")Long mailId) throws JsonProcessingException {
        mailLinkService.clicksForLinks(mailId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
