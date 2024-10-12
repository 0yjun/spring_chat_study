package com.chatflatform.domain.auth.controller;

import com.chatflatform.domain.auth.model.request.CreateUserRequest;
import com.chatflatform.domain.auth.model.request.LoginRequest;
import com.chatflatform.domain.auth.model.response.CreateUserResponse;
import com.chatflatform.domain.auth.model.response.LoginResponse;
import com.chatflatform.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AUTH API", description = "V1 Auth API")
@RestController
@RequestMapping("/api/v1/auth")
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

    @Operation(
            summary = "로그인 처리",
            description = "로그인 처리 진행"
    )
    @PostMapping("/login")
    public LoginResponse createUser(
            @RequestBody @Valid LoginRequest request
    ){
        return authService.login(request);
    }

    @Operation(
            summary = "get user name",
            description = "토큰으로 user 조회"
    )
    @GetMapping("/verify-token/{token}")
    public String createUser(
            @PathVariable("token") String token
    ){
        return authService.getUserFromToken(token);
    }
}
