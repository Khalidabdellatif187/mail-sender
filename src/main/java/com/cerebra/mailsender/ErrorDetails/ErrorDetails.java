package com.cerebra.mailsender.ErrorDetails;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private Date date;
    private String message;
    private String details;
}
