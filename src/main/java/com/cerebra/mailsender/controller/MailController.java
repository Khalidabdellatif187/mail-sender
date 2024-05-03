package com.cerebra.mailsender.controller;

import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.service.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final MailMapper mailMapper;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MailDto mailDto){
        return new ResponseEntity<>(mailService.saveMail(mailMapper.unMap(mailDto)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MailDto> getById(@PathVariable("id")Long id){
        Mail mail = mailService.getById(id);
        return new ResponseEntity<>(mailMapper.map(mail),HttpStatus.OK);
    }
}
