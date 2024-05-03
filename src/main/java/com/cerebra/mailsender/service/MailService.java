package com.cerebra.mailsender.service;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.model.Mail;

public interface MailService {


    String saveMail(Mail mail);

    Mail getById(Long id);
}
