package com.Spring.Security.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Spring.Security.JWT.JWTChecker;

@Configuration
public class WebConfig {
    @Autowired
    private JWTChecker jwtChecker;

    @Bean
    public PasswordEncoder EncodePassword() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain SecurityConfig(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/token").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasRole("USER")
                )
                .formLogin(formLogin -> formLogin.disable())
                // .formLogin(Customizer.withDefaults())
                .logout(logout -> logout.disable())
                .httpBasic(basic -> basic.disable());
                // .addFilterBefore(jwtChecker, UsernamePasswordAuthenticationFilter.class);
        // .logout(logout -> logout
        // .logoutUrl("/logout")
        // .logoutSuccessUrl("/")
        // .permitAll()
        // );

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pencoder) {
        UserDetails admin = User.builder()
                .username("admin").password(pencoder.encode("admin")).roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user").password(pencoder.encode("1234")).roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
