package com.cerebra.mailsender.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailLinkDto {
    private Long id;
    private String url;
    private LocalDateTime clickedDate;
    private Integer clickCount;

}
