package com.cerebra.mailsender.service;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.model.Mail;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.hibernate.query.Page;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface MailService {


    String saveMail(Mail mail);
    Mail findById(Long id);
    String sendEmail(Long id) throws MessagingException, JsonProcessingException;
    void deleteById(Long mailId);
    void trackMail(Long mailId);
    void updateMailStatusWhenLinksAreClicked(Long mailId);
    List<MailDto> getAllMails();

}
