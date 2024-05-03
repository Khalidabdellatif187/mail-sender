package com.cerebra.mailsender.service.implementation;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.repository.MailRepository;
import com.cerebra.mailsender.service.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;


    @Override
    public String saveMail(Mail mail) {
        mailRepository.save(mail);
        return "Mail Is Saved Successfully With Id " + mail.getId();
    }

}
