package com.yongin.complaint.Service.WebSocket.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // 웹 소켓 관련 설정을 자동으로
public class WebSocketConfiguration implements WebSocketConfigurer {
    private MyWebSocketHandler myWebSocketHandler;
    @Autowired
    public WebSocketConfiguration(MyWebSocketHandler myWebSocketHandler){
        this.myWebSocketHandler =myWebSocketHandler;
    }

    @Override // 메소드 customize
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("/chat/{roomId}");
        registry.addHandler(myWebSocketHandler, "/chat/{roomId}") // 웹소켓 서버 entrypoint
                .setAllowedOrigins("*"); //CORS, 허용할 uri를 지정한다. (default는 same-origin만 허용)
    }

//    @Bean
//    public WebSocketHandler webSocketHandler() { return new MyWebSocketHandler(); }
}
