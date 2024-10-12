package com.chatflatform.domain.auth.model.response;


import com.chatflatform.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "로그인 성공 response")
public record LoginResponse(
        @Schema(description = "error code")
        ErrorCode description,

        @Schema(description = "jwt token")
        String token
//        @Schema(description = "성공 유무 작성 코드")
//        @NotBlank
//        @NotNull
//        String code
){}
