package com.Spring.Security.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.Spring.Security.JWT.JwtService;
import com.Spring.Security.Model.UserModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;




@RestController
public class JWTController {

    @Autowired
    private JwtService jwtserv;


    
    
    @PostMapping("/register")
    public String postUser(@RequestBody UserModel user) {
        
        return jwtserv.CreateUser(user);
    }
    
    // @GetMapping("/")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
}
