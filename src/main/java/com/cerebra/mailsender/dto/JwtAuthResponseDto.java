package com.cerebra.mailsender.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponseDto {

    private String accessToken;

    private String tokenType = "Bearer";


    public JwtAuthResponseDto(){

    }
    public JwtAuthResponseDto(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public JwtAuthResponseDto(String token) {

    }

    @Override
    public String toString() {
        return "JwtAuthResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
