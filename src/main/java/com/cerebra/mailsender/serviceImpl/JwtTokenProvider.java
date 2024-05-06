package com.cerebra.mailsender.serviceImpl;

import com.cerebra.mailsender.exception.ApiExceptions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
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
            Claims claims =  Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
            System.out.println(claims);
            return true;
        }catch (SignatureException ex) {
            System.out.println("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.");
            System.out.println("Token: " + token);
            System.out.println("Expected Key: " + jwtSecretKey);
            throw ex;
        }
        catch (Exception ex){
            throw new ApiExceptions(HttpStatus.BAD_REQUEST , "Jwt Secret is invalid");
        }
    }


}
