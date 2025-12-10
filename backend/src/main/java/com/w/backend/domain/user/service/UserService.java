package com.w.backend.domain.user.service;

import com.w.backend.domain.user.dto.UserJoinRequest;
import com.w.backend.domain.user.dto.UserResponse;
import com.w.backend.domain.user.entity.User;
import com.w.backend.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void join(UserJoinRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.create(request.username(), encodedPassword);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> {
                System.out.println(">>>> [DEBUG] 예외 발생 위치: UserService");
                System.out.println(">>>> [DEBUG] 유저 확인 불가: " + username);
                return new UsernameNotFoundException("유저 확인 불가: " + username);
            });
        return UserResponse.from(user);
    }

}
