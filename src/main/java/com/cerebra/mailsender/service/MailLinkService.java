package com.cerebra.mailsender.service;

import com.cerebra.mailsender.dto.MailLinkDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MailLinkService {


    List<MailLinkDto> findByMailId(Long mailId);
    public void clicksForLinks(Long mailId) throws JsonProcessingException;
}
