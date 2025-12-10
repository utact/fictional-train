package com.w.backend.domain.auth.dto;

public record UserLoginRequst(
    String username,
    String password
) {

}
