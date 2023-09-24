package com.yongin.complaint.Service.WebSocket.impl;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

public class webSocketHandler extends TextWebSocketHandler {

    HashMap<String, WebSocketSession> sessionHashMap = new HashMap<>(); // 웹소켓 세션을 담아둘 맵

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 소켓 연결시
        super.afterConnectionEstablished(session);
        sessionHashMap.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 소켓 종료시
        sessionHashMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 메시지 발송시
        String msg = message.getPayload();
        for(String key : sessionHashMap.keySet()) {
            WebSocketSession wss = sessionHashMap.get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
