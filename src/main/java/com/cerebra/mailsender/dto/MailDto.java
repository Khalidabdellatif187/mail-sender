package com.cerebra.mailsender.dto;


import com.cerebra.mailsender.enums.MailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private Long id;
    private String body;
    private String subject;
    private String sender;
    private String recipient;
    private String messageId;
    private MailStatus mailStatus = MailStatus.DRAFT;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime sentDate;
    private LocalDateTime eventDate;
}
