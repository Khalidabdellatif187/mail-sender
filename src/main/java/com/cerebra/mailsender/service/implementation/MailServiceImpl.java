package com.cerebra.mailsender.service.implementation;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.repository.MailRepository;
import com.cerebra.mailsender.service.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String sender;
    private final MailRepository mailRepository;
    private final MailMapper mailMapper;


    @Override
    public String saveMail(Mail mail) {
        mail.setSender(sender);
        mailRepository.save(mail);
        return "Mail Is Saved Successfully With Id " + mail.getId();
    }

    @Override
    public Mail getById(Long id) {
        Mail mail = mailRepository.findById(id).orElseThrow();
        return mail;
    }

}
