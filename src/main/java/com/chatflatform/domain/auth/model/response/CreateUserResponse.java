package com.chatflatform.domain.auth.model.response;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "USER 생성 response")
public record CreateUserResponse(
        @Schema(description = "성공 유무 작성 코드")
        @NotBlank
        @NotNull
        String code
){}
