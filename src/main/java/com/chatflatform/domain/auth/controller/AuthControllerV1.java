package com.chatflatform.domain.auth.controller;

import com.chatflatform.domain.auth.model.request.CreateUserRequest;
import com.chatflatform.domain.auth.model.response.CreateUserResponse;
import com.chatflatform.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH API", description = "V1 Auth API")
@RestController("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {
    private final AuthService authService;

    @Operation(
            summary = "새로운 유저를 생성",
            description = "새로운 유저 생성"
    )
    @PostMapping("/create-user")
    public CreateUserResponse createUser(
            @RequestBody @Valid CreateUserRequest request
            ){
        return authService.createUser(request);
    }
}
