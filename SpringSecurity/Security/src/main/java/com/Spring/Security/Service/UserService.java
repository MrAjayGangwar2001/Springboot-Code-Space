package com.Spring.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Spring.Security.Dto.LoginDto;
import com.Spring.Security.JWT.JwtService;
import com.Spring.Security.Model.UserModel;
import com.Spring.Security.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder EncodePass;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtService jservice;

    public String CreateUser(UserModel user){
        System.out.println("Entered in createuser");
        user.setPassword(EncodePass.encode(user.getPassword()));

        userRepo.save(user);

        return jservice.CreateToken(user);
    }


    public String LoginUser(LoginDto login){

        Optional<UserModel> user = userRepo.findByEmail(login.getEmail());

        if (user.isPresent()) {
            UserModel um = user.get();

            if (EncodePass.matches(login.getPassword(), um.getPassword())) {
                String Token = jservice.CreateToken(um);
                return "Token : "+Token;
            }
        }
        return "invalid Password";
    }
}
