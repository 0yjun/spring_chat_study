package com.chatflatform.domain.chat.controller;

import com.chatflatform.domain.auth.model.request.CreateUserRequest;
import com.chatflatform.domain.auth.model.request.LoginRequest;
import com.chatflatform.domain.auth.model.response.CreateUserResponse;
import com.chatflatform.domain.auth.model.response.LoginResponse;
import com.chatflatform.domain.auth.service.AuthService;
import com.chatflatform.domain.chat.model.response.ChatListResponse;
import com.chatflatform.domain.chat.service.ChatServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CHAT API", description = "V1 Chat API")
@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatControllerV1 {
    private final ChatServiceV1 chatServiceV1;

    @Operation(
            summary = "채팅 리스트 조회",
            description = "가장 최신 10개의 채팅 리스트 조회"
    )
    @GetMapping("/chat-list")
    public ChatListResponse chatList(
            @RequestParam("name") @Valid String to,
            @RequestParam("from") @Valid String from
            ){
        return chatServiceV1.chatList(from,to);
    }
}
