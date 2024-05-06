package com.cerebra.mailsender.service;

import com.cerebra.mailsender.dto.JwtAuthResponseDto;
import com.cerebra.mailsender.dto.SignIn;
import com.cerebra.mailsender.dto.SignUp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {


    JwtAuthResponseDto signIn (SignIn signin);

    String signUp(SignUp signup);

}
