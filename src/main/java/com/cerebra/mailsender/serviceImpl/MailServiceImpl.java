package com.cerebra.mailsender.serviceImpl;


import com.cerebra.mailsender.clients.EventMailClient;
import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.dto.MailLinkDto;
import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.exception.ResourceNotFoundExceptions;
import com.cerebra.mailsender.mapper.MailLinkMapper;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.model.MailLink;
import com.cerebra.mailsender.repository.MailLinkRepository;
import com.cerebra.mailsender.repository.MailRepository;
import com.cerebra.mailsender.service.MailLinkService;
import com.cerebra.mailsender.service.MailService;
import com.cerebra.mailsender.utility.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String sender;
    private final MailRepository mailRepository;
    private final MailMapper mailMapper;
    private final JavaMailSender javaMailSender;


    @Transactional
    @Override
    public String saveMail(Mail mail) {
        mail.setSender(sender);
        saveLinksWithMail(mail);
        mailRepository.save(mail);
        return "Mail Is Saved Successfully With Id " + mail.getId();
    }

    private void saveLinksWithMail(Mail mail){
        MailDto mailDto = mailMapper.map(mail);
        List<MailLink> mailLinks = new ArrayList<>();
        if (mailDto.getMailLinks() != null){
            for (MailLinkDto mailLinkDto : mailDto.getMailLinks()){
                MailLink newMailLink = new MailLink();
                newMailLink.setMail(mail);
                newMailLink.setUrl(mailLinkDto.getUrl());
                mailLinks.add(newMailLink);
            }
            mail.setMailLinks(mailLinks);
        }
    }

    @Override
    public Mail getMailWithLinksById(Long id) throws JsonProcessingException {
        Mail mail = mailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExceptions("mail" , "id" , id));
        return mail;
    }

    @Override
    public Mail findById(Long id) {
        Mail mail = mailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExceptions("mail" , "id" , id));
        return mail;
    }


    @Override
    public String sendEmail(Long id) throws MessagingException, JsonProcessingException {
        Mail mail = mailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExceptions("mail" , "id" , id));
        MimeMessage mimeMailMessage = createMailMessage(mail);
        return sendMailMessage(mimeMailMessage,mail);
    }


    @SneakyThrows
    private MimeMessage createMailMessage(Mail mail){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, true);
        mailMessage.setTo(mail.getRecipient());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setFrom(mail.getSender());
        String trackingUrl = "https://abd1-154-182-32-10.ngrok-free.app/track/"+mail.getId();
        StringBuilder htmlContent = new StringBuilder("<html><head></head><body>");
        htmlContent.append("<img src=\"" + trackingUrl + "\" width=\"1\" height=\"1\" style=\"display:none;\"/>");
        htmlContent.append("<p>").append(mail.getBody()).append("</p>");
        if (mail.getMailLinks() != null){
            for (MailLink link : mail.getMailLinks()) {
                String linkTrackingUrl = "https://abd1-154-182-32-10.ngrok-free.app/track/trackLink/" + mail.getId() + "/" + link.getId();
                htmlContent.append("<p><a href='").append(linkTrackingUrl).append("' target='_blank'>").append(link.getUrl()).append("</a></p>");
            }
            htmlContent.append("</body></html>");
        }
        mailMessage.setText(htmlContent.toString(), true);
        return mimeMessage;
    }

    @SneakyThrows
    private String sendMailMessage(MimeMessage mailMessage, Mail mail){
        mail.setSentDate(LocalDateTime.now());
        try {
            mail.setMailStatus(MailStatus.SENT);
            javaMailSender.send(mailMessage);
            mail.setEventDate(LocalDateTime.now(ZoneId.of("Asia/Riyadh")));
            mail.setMailStatus(MailStatus.DELIVERED);
            return "Mail Is Sent Successfully";
        } catch (Exception e) {
            mail.setMailStatus(MailStatus.FAILED);
            mail.setEventDate(LocalDateTime.now(ZoneId.of("Asia/Riyadh")));
            return e.getMessage();
        } finally {
            String mailMessageId = mailMessage.getMessageID();
            if (mailMessageId != null && mailMessageId.startsWith("<") && mailMessageId.endsWith(">")) {
                mailMessageId = mailMessageId.substring(1, mailMessageId.length() - 1);
                mail.setMessageId(mailMessageId);
            }
            mailRepository.save(mail);
        }
    }




    @Override
    public void deleteById(Long mailId) {
        Mail mail =  mailRepository.findById(mailId).orElseThrow(() ->
                new ResourceNotFoundExceptions("mail" , "id" , mailId));
       mailRepository.deleteById(mail.getId());
    }

    @Override
    public void trackMail(Long mailId) {
        Mail mail = findById(mailId);
        if (mail != null){
            mail.setMailStatus(MailStatus.OPENED);
            mail.setEventDate(LocalDateTime.now(ZoneId.of("Asia/Riyadh")));
        }
        mailRepository.save(mail);
    }

    @Override
    public void updateMailStatusWhenLinksAreClicked(Long mailId) {
        mailRepository.updateMailStatusWhenLinksAreClicked(mailId,LocalDateTime.now(ZoneId.of("Asia/Riyadh")),MailStatus.CLICKED);
    }


}
