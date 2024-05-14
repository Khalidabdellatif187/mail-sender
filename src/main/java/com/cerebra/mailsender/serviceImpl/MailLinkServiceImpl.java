package com.cerebra.mailsender.serviceImpl;
import com.cerebra.mailsender.dto.MailLinkDto;
import com.cerebra.mailsender.mapper.MailLinkMapper;
import com.cerebra.mailsender.model.MailLink;
import com.cerebra.mailsender.repository.MailLinkRepository;
import com.cerebra.mailsender.service.MailLinkService;
import com.cerebra.mailsender.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MailLinkServiceImpl implements MailLinkService {

    private final MailLinkRepository mailLinkRepository;
    private final MailLinkMapper mailLinkMapper;
    private final MailService mailService;
    @Value("${domain.name}")
    private String domainName;

    @Override
    public List<MailLinkDto> findByMailId(Long mailId) {
        List<MailLink> mailLinks = mailLinkRepository.findByMailId(mailId);
        List<MailLinkDto> mailLinkDtos = mailLinkMapper.mapToList(mailLinks);
        return mailLinkDtos;
    }

    @Override
    public MailLink updateMailLinkCountOfClicks(Long mailId, Long linkId) {
        MailLink mailLink = mailLinkRepository.findById(linkId).orElseThrow();
        mailLink.setClickCount(mailLink.getClickCount() + 1);
        mailLinkRepository.updateClickedCountForLinks(mailLink.getClickCount() , mailId , linkId);
        mailService.updateMailStatusWhenLinksAreClicked(mailId);
        return mailLink;
    }


}
