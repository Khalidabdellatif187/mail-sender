package com.cerebra.mailsender.service;

import com.cerebra.mailsender.dto.MailLinkDto;
import com.cerebra.mailsender.model.MailLink;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MailLinkService {


    List<MailLinkDto> findByMailId(Long mailId);


    MailLink updateMailLinkCountOfClicks(Long mailId, Long linkId);


}
