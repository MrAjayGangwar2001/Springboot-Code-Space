package com.Security.SpringJWT.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Security.SpringJWT.Auth.JWTChecker;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final JWTChecker jwtChecker;

    @Bean
    public PasswordEncoder EncodePassword(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                                            .requestMatchers("/", "/register", "/login").permitAll()
                                            .requestMatchers("/user").authenticated()
                                            .requestMatchers("/admin").authenticated()
                                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form.disable())
                    .httpBasic(basic -> basic.disable())
                    .logout(logout -> logout.disable())
                    .addFilterBefore(jwtChecker, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
