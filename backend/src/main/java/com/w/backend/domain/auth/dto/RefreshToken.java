package com.w.backend.domain.auth.dto;

public class RefreshToken {
    private String key;
    private String value;

    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public RefreshToken() {}

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
    
}
