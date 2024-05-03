package com.cerebra.mailsender.service.implementation;

import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.service.MailService;
import com.cerebra.mailsender.service.SendMail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendMail {

    private final MailService mailService;
    private final JavaMailSender javaMailSender;


    @Transactional
    @Override
    public String sendEmail(Long id) {
        Mail mail = mailService.getById(id);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getRecipient());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setFrom(mail.getSender());
        mailMessage.setText(mail.getBody());
        javaMailSender.send(mailMessage);
        mail.setMailStatus(MailStatus.SENT);
        mail.setSentDate(LocalDateTime.now());
        mailService.saveMail(mail);
        return "Mail Is Sent Successfully";
    }




}
