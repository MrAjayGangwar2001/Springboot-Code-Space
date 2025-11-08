package com.Security.SpringJWT.Service;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Security.SpringJWT.Auth.JwtService;
import com.Security.SpringJWT.Dto.LoginDto;
import com.Security.SpringJWT.Model.UserModel;
import com.Security.SpringJWT.Repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;

    private final PasswordEncoder EncodePassword;
    private final UserRepo urepo;


    public String CreateUser(UserModel user){
        System.out.println("WE ENTERED IN CREATE USER FUNCTION");
        user.setPassword(EncodePassword.encode(user.getPassword()));

        urepo.save(user);
        System.out.println("WE ARE TOO CLOSE OF RETURN");
        return jwtService.createToken(user);
    }

    public String LoginUser(LoginDto login){

        Optional<UserModel> user = urepo.findByEmail(login.getEmail());

        if (user.isPresent()) {
            
            UserModel um = user.get();

            if (EncodePassword.matches(login.getPassword(), um.getPassword())) {
                
                String Token = jwtService.createToken(um);
                return Token;
            }
        }
        return "Wrong Passwords";
    }

    
}
