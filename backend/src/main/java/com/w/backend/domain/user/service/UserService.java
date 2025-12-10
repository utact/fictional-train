package com.w.backend.domain.user.service;

import com.w.backend.domain.user.dto.UserJoinRequest;
import com.w.backend.domain.user.entity.User;
import com.w.backend.domain.user.repository.UserRepository;
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

}
