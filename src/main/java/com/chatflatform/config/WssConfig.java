package com.chatflatform.config;


import com.chatflatform.component.WssHandlerV1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket //WebSocket 활성화
public class WssConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*");
    }

    //    private final WssHandlerV1 wssHandlerV1;

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//             registry.addHandler(wssHandlerV1, "/ws/v1/chat")
//                 .setAllowedOrigins("*");
//    }
}


