package com.cerebra.mailsender.controller;


import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.model.MailLink;
import com.cerebra.mailsender.repository.MailLinkRepository;
import com.cerebra.mailsender.repository.MailRepository;
import com.cerebra.mailsender.service.MailLinkService;
import com.cerebra.mailsender.service.MailService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import static org.mapstruct.ap.internal.gem.XmlElementDeclGem.build;

@RestController
@RequestMapping("/track")
@RequiredArgsConstructor
public class TrackingController {



    private final MailService mailService;
    private final MailLinkService mailLinkService;
    @GetMapping("/{mailId}")
    public void trackMail(@PathVariable Long mailId , HttpServletResponse response) throws IOException {
        System.out.println("Email opened: " + mailId);
        mailService.trackMail(mailId);
        response.setContentType("image/png");
        byte[] pixel = new byte[]{(byte) 137, (byte) 80, (byte) 78, (byte) 71, (byte) 13, (byte) 10, (byte) 26, (byte) 10, (byte) 0, (byte) 0, (byte) 0, (byte) 13, (byte) 73, (byte) 72, (byte) 68, (byte) 82, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 8, (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) -104, (byte) -99, (byte) 72, (byte) 62, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 73, (byte) 69, (byte) 78, (byte) 68, (byte) -82, (byte) 66, (byte) 96, (byte) -126};
        try (OutputStream os = response.getOutputStream()) {
            os.write(pixel);
        }
    }


    @GetMapping("/trackLink/{mailId}/{linkId}")
    public ResponseEntity<Void> trackLink(@PathVariable Long mailId, @PathVariable Long linkId) {
        System.out.println("Link clicked: Mail ID = " + mailId + ", Link ID = " + linkId);
        MailLink mailLink = mailLinkService.updateMailLinkCountOfClicks(mailId,linkId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, mailLink.getUrl())
                .build();
    }

}
