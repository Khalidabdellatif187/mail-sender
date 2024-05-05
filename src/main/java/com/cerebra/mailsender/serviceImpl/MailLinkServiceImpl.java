package com.cerebra.mailsender.serviceImpl;

import com.cerebra.mailsender.clients.EventMailClient;
import com.cerebra.mailsender.dto.MailLinkDto;
import com.cerebra.mailsender.exception.ResourceNotFoundExceptions;
import com.cerebra.mailsender.mapper.MailLinkMapper;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.model.MailLink;
import com.cerebra.mailsender.repository.MailLinkRepository;
import com.cerebra.mailsender.service.MailLinkService;
import com.cerebra.mailsender.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MailLinkServiceImpl implements MailLinkService {

    private final MailLinkRepository mailLinkRepository;
    private final MailLinkMapper mailLinkMapper;
    private final MailService mailService;
    private final EventMailClient eventMailClient;
    @Value("${domain.name}")
    private String domainName;

    @Override
    public List<MailLinkDto> findByMailId(Long mailId) {
        List<MailLink> mailLinks = mailLinkRepository.findByMailId(mailId);
        List<MailLinkDto> mailLinkDtos = mailLinkMapper.mapToList(mailLinks);
        return mailLinkDtos;
    }


    @Override
    @Transactional
    public void clicksForLinks(Long mailId) throws JsonProcessingException {
        Mail mail = mailService.findById(mailId);
        List<MailLinkDto> mailLinkDtos = findByMailId(mailId);
        if (mailLinkDtos != null || !mailLinkDtos.isEmpty()) {
            String jsonData = eventMailClient.getClickedLinkEvents(domainName, mail.getMessageId(), "clicked");
            JsonNode jsonNode = new ObjectMapper().readTree(jsonData);
            if (jsonNode != null && !jsonNode.isEmpty()) {
                JsonNode items = jsonNode.path("items");
                Map<String, Integer> urlCount = new HashMap<>();
                for (MailLinkDto dto : mailLinkDtos) {
                    urlCount.put(dto.getUrl(), 0);  // Initialize counts for each URL in MailLinkDto list
                }
                items.forEach(item -> {
                    String url = item.path("url").asText();  // Assuming each item has a 'url' field
                    if (urlCount.containsKey(url)) {
                        urlCount.put(url, urlCount.get(url) + 1);
                    }
                });

                urlCount.forEach((url, count) -> {
                    mailLinkRepository.updateClickedCountForLinks(count, mailId, url);
                });

            }
        }
    }
}
