package com.cerebra.mailsender.mapper;


import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.dto.MailLinkDto;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.model.MailLink;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MailLinkMapper {


    MailLinkDto map(MailLink mailLink);

    MailLink unMap(MailLinkDto mailLinkDto);

    List<MailLinkDto> mapToList(List<MailLink> mailLinkList);
}
