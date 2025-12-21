package com.w.backend.domain.user.entity;

import com.w.backend.global.error.CustomException;
import com.w.backend.global.error.ErrorCode;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {

    private Long id;
    private String username;
    private String password;
    private String role;

    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User create(String username, String password) {
        return new User(null, username, password, "USER");
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void changePassword(String newPassword, PasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(newPassword, this.password)) {
            throw new CustomException(ErrorCode.SAME_PASSWORD);
        }
        this.password = passwordEncoder.encode(newPassword);
    }

}
