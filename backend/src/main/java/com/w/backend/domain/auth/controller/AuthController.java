package com.w.backend.domain.auth.controller;

import com.w.backend.domain.auth.dto.UserLoginRequst;
import com.w.backend.domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody UserLoginRequst request) {
        authService.login(request);
        return ResponseEntity.ok().build();
    }

}
