package com.w.backend.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "회원가입 요청 DTO")
public record UserJoinRequest(
    @Schema(description = "사용자 ID", example = "tester")
    @NotBlank(message = "사용자 ID는 필수입니다.")
    @Size(min = 4, message = "사용자 ID는 최소 4자 이상이어야 합니다.")
    String username,

    @Schema(description = "비밀번호", example = "1234")
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    String password
) {
}
