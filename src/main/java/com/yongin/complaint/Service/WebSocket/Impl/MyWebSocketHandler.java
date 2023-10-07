package com.yongin.complaint.Service.WebSocket.Impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
     HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); // 웹소켓 세션을 담아둘 맵
    /**
     * 채팅방 목록 > 채팅방 별 세션들
     */
//    private List<HashMap<String, Object>> roomListSessions = new ArrayList<>(); // 웹소켓 세션을 담아둘 리스트

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 메시지 발송시
        String msg = message.getPayload();
        JSONObject jsonObject = jsonToObjectParser(msg);

        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(jsonObject.toJSONString()));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

//        // 채팅중인 방번호
//        String roomId = (String)jsonObject.get("roomId");
//        HashMap<String, Object> chatRoom = null;
//
//        // 채팅방이 존재할 때
//        if(roomListSessions.size() > 0){
//            for(int i = 0; i < roomListSessions.size(); ++i) {
//                String tempRoomId = (String) roomListSessions.get(i).get("roomId");
//
//                // 해당하는 방번호를 가진 세션리스트의 Object 검색
//                if (tempRoomId.equals(roomId)) {
//                    chatRoom = roomListSessions.get(i);
//                    break;
//                }
//            }
//
//            for(String key : chatRoom.keySet()){
//                if(key.equals("roomId")) continue; // 채팅방 Id 스킵
//
//                WebSocketSession wss = (WebSocketSession) chatRoom.get(key);
//
//                if(wss != null){
//                    try {
//                        wss.sendMessage(new TextMessage(jsonObject.toJSONString()));
//                    }
//                    catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        session.sendMessage(new TextMessage(obj.toJSONString()));


        // 소켓 연결시
//        super.afterConnectionEstablished(session);
//        boolean roomExist = false;
//        String url = session.getUri().toString();
//
//        System.out.println(url);
//
//        String tempRoomId = url.split("/chat/")[1];
//        int roomIdx = roomListSessions.size();
//
//        if(roomIdx > 0){
//            for(int i = 0; i < roomIdx; ++i){
//                String roomId = (String)roomListSessions.get(i).get("roomId");
//
//                if(roomId.equals(tempRoomId)){
//                    roomExist = true;
//                    roomIdx = i;
//                    break;
//                }
//            }
//        }
//
//        if(roomExist){ // 이미 존재하는 방에 세션 추가
//            HashMap<String, Object> chatRoom = roomListSessions.get(roomIdx);
//            chatRoom.put(session.getId(), session);
//        }
//        else{
//            HashMap<String, Object> newChatRoom = new HashMap<>();
//            newChatRoom.put("roomId", tempRoomId);
//            newChatRoom.put(session.getId(), session);
//            roomListSessions.add(newChatRoom);
//        }
//
//        // 세션 등록이 끝나면 발급 받은 세션 ID 값의 메시지 발송
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("type", "getId");    // 발신 메시지 타입
//        jsonObject.put("sessionId", session.getId());   // sessionId
//        session.sendMessage(new TextMessage(jsonObject.toJSONString()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);

        // 소켓 종료
//        if(roomListSessions.size() > 0){ // 소켓 종료시 해당하는 세션 삭제
//            for(int i = 0; i < roomListSessions.size(); ++i){
//                roomListSessions.get(i).remove(session.getId());
//            }
//        }
//        super.afterConnectionClosed(session, status);
    }


    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject)parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
