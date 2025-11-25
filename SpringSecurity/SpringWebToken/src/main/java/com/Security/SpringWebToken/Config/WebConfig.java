package com.Security.SpringWebToken.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Security.SpringWebToken.Auth.JWTChecker;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final JWTChecker jwtcheck;

    @Bean
    public PasswordEncoder EncodePass() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain SecurityConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register").permitAll()
                        // .requestMatchers("/user", "/admin").authenticated()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
                .httpBasic(basic -> basic.disable());
                // .addFilterBefore(jwtcheck, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }
}
