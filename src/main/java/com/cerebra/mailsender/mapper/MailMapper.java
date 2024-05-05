package com.cerebra.mailsender.mapper;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.model.Mail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MailMapper{


    @Mapping(source= "mailLinks", target = "mailLinks")
    MailDto map(Mail mail);

    @Mapping(target = "mailLinks", source = "mailLinks")
    Mail unMap(MailDto mailDto);
}
