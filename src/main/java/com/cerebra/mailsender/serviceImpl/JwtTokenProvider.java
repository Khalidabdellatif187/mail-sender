package com.cerebra.mailsender.serviceImpl;

import com.cerebra.mailsender.exception.ApiExceptions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {


    @Value("${app.jwt-secret}")
    private String jwtSecretKey;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationMilliseconds;

    public String generateToken(Authentication authentication){

        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationMilliseconds);
        String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date())
                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512,jwtSecretKey).compact();

        return token;
    }


    public String getUserNameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }


    public boolean isExist(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception ex){
            throw new ApiExceptions(HttpStatus.BAD_REQUEST , "Jwt Secret is invalid");
        }
    }


}
