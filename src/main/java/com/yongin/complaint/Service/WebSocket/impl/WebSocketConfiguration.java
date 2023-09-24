package com.yongin.complaint.Service.WebSocket.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.logging.SocketHandler;

@Configuration
@EnableWebSocket // 웹 소켓 관련 설정을 자동으로
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override // 메소드 customize
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalingSocketHandler(), "/chat/:{roomId}") // 웹소켓 서버 entrypoint
                .setAllowedOrigins("*"); //CORS, 허용할 uri를 지정한다. (default는 same-origin만 허용)
    }

    @Bean
    public WebSocketHandler signalingSocketHandler() { return new webSocketHandler(); }
}
