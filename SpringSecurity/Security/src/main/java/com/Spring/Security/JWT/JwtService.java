package com.Spring.Security.JWT;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

import com.Spring.Security.Model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SecretKey = "dknidwefbebcjiecectvucukhvbcecediudue";

    private SecretKey EncodeKey() {

        return Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String CreateToken(UserModel user) {

        return Jwts.builder().subject(user.getUserId().toString())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *2))
                .signWith(EncodeKey())
                .compact();
    }

    public Claims extractClaim(String token) {

        return Jwts.parser()
                .verifyWith(EncodeKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String fetchbyId(String token) {

        return extractClaim(token).getSubject();
    }

    public String fetchByEmail(String token){
        return extractClaim(token).get("email", String.class);
    }

    public boolean isTokenExpired(String token){
        return extractClaim(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, UserModel user){
        String userId = fetchbyId(token);
        return userId.equals(user.getUserId().toString()) && !isTokenExpired(token);
    }
}
