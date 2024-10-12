package com.chatflatform.domain.chat.service;

import com.chatflatform.domain.chat.model.Message;
import com.chatflatform.domain.chat.repository.ChatRepository;
import com.chatflatform.domain.chat.repository.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class ChatServiceV1 {

    private final ChatRepository chatRepository;
    @Transactional(transactionManager = "createChatTransactionManager")
    public void saveChatMessage(Message msg){
        Chat chat = Chat.builder()
                .sender(msg.getFrom())
                .receiver(msg.getTo())
                .message(msg.getMessage())
                .create_at(new Timestamp(System.currentTimeMillis()))
                .build();
        chatRepository.save(chat);
    }
}
