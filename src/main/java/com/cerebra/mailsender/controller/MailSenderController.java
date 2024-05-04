package com.cerebra.mailsender.controller;


import com.cerebra.mailsender.service.SendMail;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/send")
public class MailSenderController {

    private final SendMail sendMail;

    @GetMapping("/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable("id")Long id) throws MessagingException {
        return new ResponseEntity<>(sendMail.sendEmail(id),HttpStatus.OK);
    }

}
