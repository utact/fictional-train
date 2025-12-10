package com.w.backend.domain.user.dto;

public record UserJoinRequest(
    String username,
    String password
) {

}
