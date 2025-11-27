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

    public String CreateUser(UserModel users) {
        // System.out.println("RAW USER = " + users);
        System.out.println("USER PASSWORD : " + users.getPassword());
        System.out.println("USER EMAIL : " + users.getEmail());
        System.out.println("USER USERNAME : " + users.getUsername());
        users.setPassword(passEncoder.encode(users.getPassword()));

        urepo.save(users);

        return jwtServ.CreateToken(users);
    }

    public String LoginUser(LoginDto login) {
        Optional<UserModel> users = urepo.findByEmail(login.getEmail());

        if (users.isPresent()) {

            UserModel um = users.get();

            if (passEncoder.matches(login.getPassword(), um.getPassword())) {

                String Token = jwtServ.CreateToken(um);
                return Token;
            }
        }
        return "INVALID PASSWORD";
    }

}
