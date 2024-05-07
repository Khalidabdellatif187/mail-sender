package com.cerebra.mailsender.mapper;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.model.Mail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MailMapper{


    @Mapping(source= "mailLinks", target = "mailLinks")
    MailDto map(Mail mail);

    @Mapping(target = "mailLinks", source = "mailLinks")
    Mail unMap(MailDto mailDto);

    List<MailDto> mapToList(List<Mail> mailList);
}
