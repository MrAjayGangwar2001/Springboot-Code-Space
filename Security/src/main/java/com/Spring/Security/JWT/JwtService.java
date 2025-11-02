package com.Spring.Security.JWT;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Spring.Security.Model.UserModel;
import com.Spring.Security.Repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SecretKey = "dknidwefbebcjiecebcecediudue";


    private SecretKey EncodeKey(){

        return Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String CreateToken(UserModel user){

        return Jwts.builder().subject(user.getUserId().toString())
                .claim("email", user.getEmail()).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60)).signWith(EncodeKey())
                .compact();
    }


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;


    public String CreateUser(UserModel user){

        user.setPassword(encoder.encode(user.getPassword()));

        userRepo.save(user);

        return " Data has been successfully Sent";
    }
}
