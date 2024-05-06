package com.cerebra.mailsender.serviceImpl;


import com.cerebra.mailsender.dto.JwtAuthResponseDto;
import com.cerebra.mailsender.dto.SignIn;
import com.cerebra.mailsender.dto.SignUp;
import com.cerebra.mailsender.exception.ApiExceptions;
import com.cerebra.mailsender.model.User;
import com.cerebra.mailsender.repository.UserRepository;
import com.cerebra.mailsender.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public JwtAuthResponseDto signIn(SignIn signin) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(signin.getUserNameOrEmail()
                        , signin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        JwtAuthResponseDto auth = new JwtAuthResponseDto(token);

        auth.setAccessToken(token);
        return  auth;
    }

    @Override
    public String signUp(SignUp signup) {
        if(userRepository.existsByUserName(signup.getUserName())) {
            throw  new ApiExceptions(HttpStatus.BAD_REQUEST,"This username is already Existed");
        }
        if(userRepository.existsByEmail(signup.getEmail())) {
            throw new ApiExceptions(HttpStatus.BAD_REQUEST , "This email is already Existed");
        }

        User user = new User();

        user.setEmail(signup.getEmail());
        user.setPassword(passwordEncoder.encode(signup.getPassword()));
        user.setUserName(signup.getUserName());


        userRepository.save(user);

        return "You registered Successfully" ;

    }
}
