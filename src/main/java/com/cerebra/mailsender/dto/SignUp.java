package com.cerebra.mailsender.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUp {
    private String name;
    private String userName;
    private String password;
    private String email;

    public SignUp(String name, String userName, String password, String email) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public SignUp(){

    }
    @Override
    public String toString() {
        return "SignUp{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
