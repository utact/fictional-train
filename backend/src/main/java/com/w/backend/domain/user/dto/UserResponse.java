package com.w.backend.domain.user.dto;

import com.w.backend.domain.user.entity.User;

public record UserResponse(String username) {

    public static UserResponse from(User user) {
        return new UserResponse(
            user.getUsername()
        );
    }
    
}
