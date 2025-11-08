package com.Security.SpringJWT.Auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.Security.SpringJWT.Model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String jwtSecretKey = "bdhebcecjecmkemcihubxstrtyctyxtrzeywebejdnj";

    // Secret key Encoded 
    private SecretKey EncodeKey(){

        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    // To Create Token
    public String createToken(UserModel user){

        return Jwts.builder().subject(user.getUserId().toString())
                    .claim("email", user.getEmail())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *5))
                    .signWith(EncodeKey())
                    .compact();
    }

    // To find the value from Payload
    public Claims ExtractClaim(String Token){

        return Jwts.parser()
                    .verifyWith(EncodeKey())
                    .build()
                    .parseSignedClaims(Token)
                    .getPayload();
    }


    public String fetchById(String token){
        return ExtractClaim(token).getSubject();
    }

    public String fetchByEmail(String token){
        return ExtractClaim(token).get("email", String.class);

    }


    public boolean isTokenExpired(String token){
        return ExtractClaim(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, UserModel user){

        String userId = fetchById(token);

        return userId.equals(user.getUserId().toString()) && !isTokenExpired(token);
    }


    

}
