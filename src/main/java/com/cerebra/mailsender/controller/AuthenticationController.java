package com.cerebra.mailsender.controller;


import com.cerebra.mailsender.dto.JwtAuthResponseDto;
import com.cerebra.mailsender.dto.SignIn;
import com.cerebra.mailsender.dto.SignUp;
import com.cerebra.mailsender.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;


    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthResponseDto> signIn (@RequestBody SignIn signin){
        return new ResponseEntity<>(userService.signIn(signin), HttpStatus.CREATED);
    }


    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUp signup){
        return new ResponseEntity<>(userService.signUp(signup),HttpStatus.CREATED);
    }






}
