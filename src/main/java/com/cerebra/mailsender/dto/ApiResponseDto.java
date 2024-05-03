package com.cerebra.mailsender.dto;


import com.cerebra.mailsender.exception.ApiExceptionDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
@NoArgsConstructor
public class ApiResponseDto<T>{

    @Builder.Default
    private Boolean success = true;
    private ApiExceptionDto error;
    @Builder.Default
    private Integer code = HttpStatus.OK.value();
    private T payload;
    private String serviceTime;

    public ApiResponseDto(Boolean success, ApiExceptionDto error, Integer code, T payload, String serviceTime) {
        this.success = success;
        this.error = error;
        this.code = code;
        this.payload = payload;
        this.serviceTime = serviceTime;
    }

    public static <T> ApiResponseDto<T> ok(T payload) {
        return status(HttpStatus.OK, payload, null);
    }

    public static <T> ApiResponseDto<T> ok() {
        return status(HttpStatus.OK, null, null);
    }

    public static <T> ApiResponseDto<T> created(T payload) {
        return status(HttpStatus.CREATED, payload, null);
    }

    public static <T> ApiResponseDto<T> accepted(T payload) {
        return status(HttpStatus.ACCEPTED, payload, null);
    }

    public static <T> ApiResponseDto<T> ok(T payload, String serviceTime) {
        return status(HttpStatus.OK, payload, serviceTime);
    }

    public static <T> ApiResponseDto<T> created(T payload, String serviceTime) {
        return status(HttpStatus.CREATED, payload, serviceTime);
    }

    public static <T> ApiResponseDto<T> accepted(T payload, String serviceTime) {
        return status(HttpStatus.ACCEPTED, payload, serviceTime);
    }

    public static <T> ApiResponseDto<T> status(HttpStatus status, T payload, String serviceTime) {
        return new ApiResponseDto<>(true, null, status.value(), payload, serviceTime);
    }

    public static <T> ApiResponseDto<T> noContent() {
        return status(HttpStatus.NO_CONTENT, null, null);
    }


}
