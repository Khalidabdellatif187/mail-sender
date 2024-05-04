package com.cerebra.mailsender.service;

import jakarta.mail.MessagingException;

public interface SendMail {


    String sendEmail(Long id) throws MessagingException;
}
