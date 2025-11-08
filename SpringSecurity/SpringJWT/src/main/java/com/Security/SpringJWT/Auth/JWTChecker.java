package com.Security.SpringJWT.Auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Security.SpringJWT.Model.UserModel;
import com.Security.SpringJWT.Repository.UserRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTChecker extends OncePerRequestFilter {

    private final JwtService jservice;
    private final UserRepo urepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String AuthHeader = request.getHeader("Authorization");

        if (AuthHeader == null || !AuthHeader.startsWith("Bearer")) {

            filterChain.doFilter(request, response);

            return;
        }

        String Token = AuthHeader.substring(7);

        if (jservice.isTokenExpired(Token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String emailChecker = jservice.fetchByEmail(Token);
        Optional<UserModel> user = urepo.findByEmail(emailChecker);
        if (user.isPresent()) {
            UserModel um = user.get();

            if (jservice.isTokenValid(Token, um)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, 
                        um.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request, response);
        }

    }

}
