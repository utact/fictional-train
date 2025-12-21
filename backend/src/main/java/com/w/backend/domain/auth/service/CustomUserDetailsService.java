package com.w.backend.domain.auth.service;

import com.w.backend.domain.user.entity.User;
import com.w.backend.domain.user.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findByUsername(username)
            .orElseThrow(() -> {
                System.out.println(">>>> [DEBUG] 예외 발생 위치: CustomUserDetailsService");
                System.out.println(">>>> [DEBUG] 유저 확인 불가: " + username);
                return new UsernameNotFoundException("유저 확인 불가: " + username);
            });
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }

}
