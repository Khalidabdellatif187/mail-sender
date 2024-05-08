package com.cerebra.mailsender.service;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.model.Mail;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface MailService {


    String saveMail(Mail mail);
    Mail getById(Long id) throws JsonProcessingException;
    String sendEmail(Long id) throws MessagingException, JsonProcessingException;
    void setLastEventMailStatus(Mail mail) throws JsonProcessingException;
    List<MailDto> getAllMails();
    void deleteById(Long mailId);

}
