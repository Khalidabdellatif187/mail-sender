package com.cerebra.mailsender.exception;

import org.springframework.http.HttpStatus;

public class ApiExceptions extends RuntimeException{


    private HttpStatus status;
    private String message;

    public ApiExceptions(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiExceptions(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }


    @Override
    public String getMessage(){
        return message;
    }

}
