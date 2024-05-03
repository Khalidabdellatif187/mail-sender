package com.cerebra.mailsender.controller;

import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.service.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;
    private final MailMapper mailMapper;

    @Autowired
    public MailController(MailService mailService, MailMapper mailMapper) {
        this.mailService = mailService;
        this.mailMapper = mailMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MailDto mailDto){
        return new ResponseEntity<>(mailService.saveMail(mailMapper.unMap(mailDto)), HttpStatus.CREATED);
    }
}
