package com.Spring.Security.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.Spring.Security.Dto.LoginDto;
import com.Spring.Security.JWT.JwtService;
import com.Spring.Security.Model.UserModel;
import com.Spring.Security.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;




@RestController
public class JWTController {

    @Autowired
    private UserService userv;


    @GetMapping("/")
    public String Home() {
        return "🏚️ YOU ARE ON HOME PAGE ! NO LOGIN NEEDED.";
    }
    @GetMapping("/user")
    public String User() {
        return "🏚️ YOU ARE ON USER PAGE & YOU ARE LOGIN.";
    }
    @GetMapping("/admin")
    public String Admin() {
        return "🏚️ YOU ARE ON ADMIN PAGE ! YOU ARE LOGGED IN.";
    }
    
    
    @PostMapping("/register")
    public String postUser(@RequestBody UserModel user) {
        
        return userv.CreateUser(user);
    }
    
    @PostMapping("/login")
    public String LoginUser(@RequestBody LoginDto login) {
        
        return userv.LoginUser(login);
    }
    
}
