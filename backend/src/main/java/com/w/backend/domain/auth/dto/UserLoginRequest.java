package com.w.backend.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "로그인 요청 DTO")
public record UserLoginRequest(
    @Schema(description = "사용자 ID", example = "tester")
    @NotBlank(message = "사용자 ID는 필수입니다.")
    String username,

    @Schema(description = "비밀번호", example = "1234")
    @NotBlank(message = "비밀번호는 필수입니다.")
    String password
) {
}
