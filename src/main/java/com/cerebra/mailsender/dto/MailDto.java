package com.cerebra.mailsender.dto;


import com.cerebra.mailsender.enums.MailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private MailStatus mailStatus;

}
