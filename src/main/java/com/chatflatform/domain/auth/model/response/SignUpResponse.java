package com.chatflatform.domain.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "USER 생성")
public record SignUpResponse (
        @Schema(description = "유저 이름")
        @NotBlank
        @NotNull
        String name,
        @Schema(description = "유저 패스워드")
        @NotBlank
        @NotNull
        String password
){}