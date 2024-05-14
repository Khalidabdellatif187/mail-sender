package com.cerebra.mailsender.controller;
import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;;
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



    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MailDto mailDto){
        return new ResponseEntity<>(mailService.saveMail(mailMapper.unMap(mailDto)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MailDto> getById(@PathVariable("id")Long id) {
        Mail mail = mailService.findById(id);
        return new ResponseEntity<>(mailMapper.map(mail),HttpStatus.OK);
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable("id")Long id) throws MessagingException, JsonProcessingException {
        return new ResponseEntity<>(mailService.sendEmail(id),HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMailById(@PathVariable("id")Long id){
        mailService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/mails")
    public ResponseEntity<List<MailDto>> getAllMails(){
        return new ResponseEntity<>(mailService.getAllMails(),HttpStatus.OK);
    }







}
