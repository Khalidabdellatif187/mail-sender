package com.cerebra.mailsender.service;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.model.Mail;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface MailService {


    String saveMail(Mail mail);
    Mail getById(Long id) throws JsonProcessingException;
    String sendEmail(Long id) throws MessagingException, JsonProcessingException;
    void setLastEventMailStatus(Mail mail) throws JsonProcessingException;

    Mail findById(Long id);
}
