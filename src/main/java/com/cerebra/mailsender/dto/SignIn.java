package com.cerebra.mailsender.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignIn {

    private String userNameOrEmail;
    private String password;

    @Override
    public String toString() {
        return "SignIn{" +
                "userNameOrEmail='" + userNameOrEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public SignIn(String userNameOrEmail, String password) {
        this.userNameOrEmail = userNameOrEmail;
        this.password = password;
    }

    public SignIn(){

    }



}
