package com.cerebra.mailsender.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String userName;
    private String password;
}
