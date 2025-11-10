package com.Security.SpringWebToken.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Security.SpringWebToken.Auth.JWTService;
import com.Security.SpringWebToken.Dto.LoginDto;
import com.Security.SpringWebToken.Model.UserModel;
import com.Security.SpringWebToken.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository urepo;
    private final PasswordEncoder passEncoder;
    private final JWTService jwtServ;

    public String CreateUser(UserModel user) {
       
        user.setPassword(passEncoder.encode(user.getPassword()));

        urepo.save(user);

        return jwtServ.CreateToken(user);
    }

    public String LoginUser(LoginDto login) {
       Optional<UserModel> user = urepo.findByEmail(login.getEmail());

       if (user.isPresent()) {
        
        UserModel um = user.get();

        if (passEncoder.matches(login.getPassword(), um.getPassword())) {
            
            String Token = jwtServ.CreateToken(um);
            return Token;
        }
       }
       return "INVALID PASSWORD";
    }

}
