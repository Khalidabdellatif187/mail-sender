package com.cerebra.mailsender.exception;


import com.cerebra.mailsender.dto.ApiResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorDecoderException extends RuntimeException{

    private HttpStatus httpStatus;
    private  String [] params;
    private ApiResponseDto apiResponse;

    public ErrorDecoderException(String message, HttpStatus httpStatus, String [] params) {
        super(message);
        this.httpStatus = httpStatus;
        this.params = params;
    }

    public ErrorDecoderException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ErrorDecoderException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public ErrorDecoderException(HttpStatus status) {
        this.httpStatus = status;
    }

}
