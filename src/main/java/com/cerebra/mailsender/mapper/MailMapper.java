package com.cerebra.mailsender.mapper;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.model.Mail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailMapper{

    MailDto map(Mail mail);

    Mail unMap(MailDto mailDto);
}
