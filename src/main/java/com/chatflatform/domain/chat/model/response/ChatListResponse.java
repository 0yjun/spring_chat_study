package com.chatflatform.domain.chat.model.response;

import com.chatflatform.domain.chat.model.Message;
import com.chatflatform.domain.chat.repository.entity.Chat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "채팅 리스트")
public record ChatListResponse(
        @Schema(description = "chat return List: []")
        List<Message> results
) {
}
