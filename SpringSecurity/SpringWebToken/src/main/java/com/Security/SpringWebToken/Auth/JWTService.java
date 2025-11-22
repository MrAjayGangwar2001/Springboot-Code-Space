package com.Security.SpringWebToken.Auth;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Security.SpringWebToken.Model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
public class JWTService {

    private final SecretKey Key;
    private final Long Expiration;

    


    public JWTService(@Value("${JWT_SECRET}") String key,@Value("${JWT_EXPIRATION_MS}") Long expiration) {
        this.Key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        this.Expiration = expiration;

        
    }

    // private SecretKey EncodeKey() {

    //     return Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
    // }

    public String CreateToken(UserModel user) {

        return Jwts.builder()
                .subject(user.getUserId().toString())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(Key)
                .compact();

    }

    public Claims ExtractData(String token){

        return Jwts.parser()
                    .verifyWith(Key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public String fetchById(String token){

        return ExtractData(token).getSubject();
    }

    public String fetchByEmail(String token){

        return ExtractData(token).get("email", String.class);
    }

    public boolean isTokenExpired(String token){

        return ExtractData(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, UserModel user){

        String UserId = fetchById(token);
        return UserId.equals(user.getUserId().toString()) && !isTokenExpired(token);
    }
    
    
}