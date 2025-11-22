package com.Security.SpringWebToken.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Security.SpringWebToken.Dto.LoginDto;
import com.Security.SpringWebToken.Model.UserModel;
import com.Security.SpringWebToken.Service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// @CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserService userv;

    @GetMapping("/")
    public String Home() {
        return "YOU ARE ON HOME PAGE , NO LOGIN NEEDED";
    }

    @GetMapping("/user")
    public String UserHome() {
        return "YOU ARE ON USER HOME PAGE.";
    }
    @GetMapping("/admin")
    public String AdminHome() {
        return "YOU ARE ON ADMIN hOME PAGE.";
    }

    @GetMapping("/verify")
    public String verify() {
        return "USER HAS BEEN VERIFIED BY TOKEN.";
    }

    @PostMapping("/register")
    public String RegisterUser(@RequestBody UserModel user) {
       System.out.println("USER PASSWORD : "+user.getPassword());
       System.out.println("USER EMAIL : "+user.getEmail());
       System.out.println("USER USERNAME : "+user.getUsername());
        return userv.CreateUser(user);
    }

    @PostMapping("/login")
    public String UserLogin(@RequestBody LoginDto login) {
        
        return userv.LoginUser(login);
    }
    
}