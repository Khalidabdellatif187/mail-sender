package com.cerebra.mailsender.exception;

import com.cerebra.mailsender.dto.ApiResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class AppErrorDecoderException extends ErrorDecoderException{

    private ApiResponseDto apiResponse;

    public AppErrorDecoderException(String message, HttpStatus httpStatus, String [] params) {
        super(message, httpStatus, params);
    }


    public AppErrorDecoderException(ApiResponseDto response, HttpStatus httpStatus) {
        super(httpStatus);
        this.apiResponse =response;
    }

    public AppErrorDecoderException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public AppErrorDecoderException(String message) {
        super(message);
    }
}
