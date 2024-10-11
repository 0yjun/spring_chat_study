package com.chatflatform.component;

import com.chatflatform.domain.chat.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class WssHandlerV1 extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
        try{
            String payload = msg.getPayload();
            Message message = objectMapper.readValue(payload, Message.class);

            //TODO 1. DB에 있는 값이지 확인(to, from)

            //TODO 2. 채팅 메세지 저장

            session.sendMessage(new TextMessage(payload));

        }catch(Exception e){

        }
    }
}
