package com.w.backend.domain.auth.dto;

public record UserLoginRequest(
    String username,
    String password
) {

}
