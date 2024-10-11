package com.chatflatform.domain.chat.controller;


import com.chatflatform.domain.chat.model.Message;
import com.chatflatform.domain.chat.service.ChatServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WssControllerV1 {
    private final ChatServiceV1 chatServiceV1;

    @MessageMapping("/chat/message/{from}")
    @SendTo("/sub/chat")
    public Message receiveMessage(
            @DestinationVariable String from,
            Message msg
    ){
        log.info("Message Ressived {}", from);
        //TODO-> save
        chatServiceV1.saveChatMessage(msg);
        return msg;
    }
}
