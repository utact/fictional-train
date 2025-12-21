package com.w.backend.domain.auth.service;

import com.w.backend.domain.auth.dto.TokenDto;
import com.w.backend.domain.auth.dto.UserLoginRequest;
import com.w.backend.global.jwt.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public TokenDto login(UserLoginRequest request) {
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(request.username(), request.password());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        return new TokenDto(jwt);
    }

}
