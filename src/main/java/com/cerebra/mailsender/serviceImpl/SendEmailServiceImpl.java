package com.cerebra.mailsender.serviceImpl;

import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.service.MailService;
import com.cerebra.mailsender.service.SendMail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendMail {

    private final MailService mailService;
    private final JavaMailSender javaMailSender;



    @Override
    public String sendEmail(Long id) throws MessagingException {
        Mail mail = mailService.getById(id);
        MimeMessage mimeMailMessage = createMailMessage(mail);
        return sendMailMessage(mimeMailMessage,mail);
    }


    @SneakyThrows
    private MimeMessage createMailMessage(Mail mail){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, true); // true indicates multipart message
        mailMessage.setTo(mail.getRecipient());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setFrom(mail.getSender());
        mailMessage.setText(mail.getBody());
        return mimeMessage;
    }

    @SneakyThrows
    private String sendMailMessage(MimeMessage mailMessage, Mail mail){
        mail.setSentDate(LocalDateTime.now());
        try {
            javaMailSender.send(mailMessage);
            mail.setMailStatus(MailStatus.SENT);
            return "Mail Is Sent Successfully";
        } catch (Exception e) {
            mail.setMailStatus(MailStatus.FAILED);
            return e.getMessage();
        } finally {
            mail.setMessageId(mailMessage.getMessageID());
            mailService.saveMail(mail);
        }
    }












}
