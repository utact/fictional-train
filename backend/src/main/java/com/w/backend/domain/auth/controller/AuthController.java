package com.w.backend.domain.auth.controller;

import com.w.backend.domain.auth.dto.TokenDto;
import com.w.backend.domain.auth.dto.UserLoginRequest;
import com.w.backend.domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserLoginRequest request) {
        TokenDto tokenDto = authService.login(request);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenDto tokenDto) {
        TokenDto newTokenDto = authService.reissue(tokenDto);
        return ResponseEntity.ok(newTokenDto);
    }

}
