package com.cerebra.mailsender.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;


@Getter
@NoArgsConstructor
public class ApiExceptionDto {
    private String message;
    private HttpStatus httpStatus;
    private String zonedDateTime;
    private String cause;
    private String source;


    public ApiExceptionDto(String message,  HttpStatus httpStatus, String zonedDateTime, String cause, String source) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
        this.cause = cause;
        this.source = source;
    }

    public ApiExceptionDto(String message, HttpStatus httpStatus, String zonedDateTime){
        this.message= message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public ApiExceptionDto(String message,  HttpStatus httpStatus, String zonedDateTime, String source){
        this.message= message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
        this.source =source;
    }
}
