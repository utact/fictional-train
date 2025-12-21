package com.w.backend.domain.auth.service;

import com.w.backend.domain.auth.dto.RefreshToken;
import com.w.backend.domain.auth.dto.TokenDto;
import com.w.backend.domain.auth.dto.UserLoginRequest;
import com.w.backend.domain.auth.mapper.RefreshTokenMapper;
import com.w.backend.global.jwt.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenMapper refreshTokenMapper;

    public AuthService(TokenProvider tokenProvider,
                       AuthenticationManager authenticationManager,
                       RefreshTokenMapper refreshTokenMapper) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    public TokenDto login(UserLoginRequest request) {
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(request.username(), request.password());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        // RefreshToken DB 저장 (기존에 있으면 업데이트, 없으면 저장)
        Optional<RefreshToken> refreshTokenOptional = refreshTokenMapper.findByKey(authentication.getName());
        if (refreshTokenOptional.isPresent()) {
            refreshTokenMapper.update(refreshTokenOptional.get().updateValue(refreshToken));
        } else {
            refreshTokenMapper.save(new RefreshToken(authentication.getName(), refreshToken));
        }

        return new TokenDto(accessToken, refreshToken);
    }
    
    public TokenDto reissue(TokenDto requestingTokenDto) {
        // Refresh Token 기본 검증
        if (!tokenProvider.validateToken(requestingTokenDto.refreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // Access Token 속 User ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(requestingTokenDto.accessToken());

        // 저장소 속 User ID 기반 Refresh Token 값 가져오기
        RefreshToken refreshToken = refreshTokenMapper.findByKey(authentication.getName())
            .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // Refresh Token 일치 검사
        if (!refreshToken.getValue().equals(requestingTokenDto.refreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        String newAccessToken = tokenProvider.createToken(authentication);
        String newRefreshToken = tokenProvider.createRefreshToken(authentication);

        // Refresh Token DB 업데이트
        refreshTokenMapper.update(refreshToken.updateValue(newRefreshToken));

        return new TokenDto(newAccessToken, newRefreshToken);
    }

}
