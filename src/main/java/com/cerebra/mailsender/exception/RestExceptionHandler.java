package com.cerebra.mailsender.exception;


import com.cerebra.mailsender.constants.Constants;
import com.cerebra.mailsender.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    private MessageSource messageSource;

    @Value("${spring.application.name}")
    private String applicationName;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleApiRequestException(BusinessException businessException) {

        String message = messageSource.getMessage(businessException.getMessage(),
                businessException.getParams(), new Locale("en"));

        ApiExceptionDto apiException = new ApiExceptionDto(
                message,
                businessException.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z")).toString(),
                applicationName
        );
        //
        return new ResponseEntity<>(ApiResponseDto.builder().error(apiException).success(false)
                .code(businessException.getHttpStatus().value()).build(), businessException.getHttpStatus());
    }



    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        String fieldError = ex.getFieldError().getField();
        String messageKey = ex.getFieldError(fieldError).getDefaultMessage();
        //
        String message = messageSource.getMessage(messageKey,
                null, new Locale("en"));
        ApiExceptionDto apiException = new ApiExceptionDto(
                message,
                status,
                ZonedDateTime.now(ZoneId.of("Z")).toString()
        );
        //
        return new ResponseEntity<>(ApiResponseDto.builder().error(apiException).success(false)
                .code(status.value()).build(), status);
    }




    @ExceptionHandler(AppErrorDecoderException.class)
    public ResponseEntity<Object> handleFeignStatusExceptionApp(AppErrorDecoderException ex) {
        log.error("exception details  == >> {} ", ex);
        return new ResponseEntity<>(ex.getApiResponse(), ex.getApiResponse().getError().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleApiRequestException(Exception exception) {
        log.error("internal server error happened  == >> {} ", exception.getMessage());
        log.error("internal server error happened exception details  == >> {} ", exception);
        String message = messageSource.getMessage(Constants.ErrorKeys.INTERNAL_SERVER_ERROR,
                null, new Locale("en"));

        ApiExceptionDto apiException = new ApiExceptionDto(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z")).toString(),
                exception.getMessage(),
                applicationName

        );

        return new ResponseEntity<>(ApiResponseDto.builder().error(apiException).success(false)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
