package com.yongin.complaint.Service.WebSocket.Impl;

import com.yongin.complaint.JPA.Entity.ChatHistory;
import com.yongin.complaint.JPA.Repository.ChatHistoryRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
//     HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); // 웹소켓 세션을 담아둘 맵
    /**
     * 채팅방 목록 > 채팅방 별 세션들
     */
    private List<HashMap<String, Object>> roomListSessions = new ArrayList<>(); // 웹소켓 세션을 담아둘 리스트

    @Autowired
    ChatHistoryRepository chatHistoryRepository;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("roomListSessions: " + roomListSessions);
        System.out.println("roomListSessions[0]: " + roomListSessions.get(0));
        // 메시지 발송시
        String msg = message.getPayload();
        System.out.println("msg: " + msg);
        JSONObject jsonObject = jsonToObjectParser(msg);
        System.out.println("jsonObject: " + jsonObject);

        // 발송 시간 저장
        LocalDateTime chatHistoryTime = LocalDateTime.now();
        jsonObject.put("chatHistoryTime", chatHistoryTime.toString());
        System.out.println("jsonObject: " + jsonObject);

//        for(String key : sessionMap.keySet()) {
//            WebSocketSession wss = sessionMap.get(key);
//            try {
//                wss.sendMessage(new TextMessage(jsonObject.toJSONString()));
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        }

        // 채팅중인 방번호
        String chatRoomId = (String)jsonObject.get("chatRoomId");
        System.out.println("chatRoomId: " + chatRoomId);
        HashMap<String, Object> chatRoom = null;

        // 소켓이 열린 채팅방이 존재할 때
        if(roomListSessions.size() > 0){
            for(int i = 0; i < roomListSessions.size(); ++i) {
                String tempRoomId = (String) roomListSessions.get(i).get("chatRoomId");

                // 해당하는 방번호를 가진 세션리스트의 Object 검색
                if (tempRoomId.equals(chatRoomId)) {
                    chatRoom = roomListSessions.get(i);
                    break;
                }
            }
            System.out.println("chatRoom: " + chatRoom);

            for(String key : chatRoom.keySet()){
                if(key.equals("chatRoomId")) continue; // 채팅방 Id 스킵

                WebSocketSession wss = (WebSocketSession) chatRoom.get(key);

                if(wss != null){
                    try {
                        wss.sendMessage(new TextMessage(jsonObject.toJSONString()));
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            // 세션 빼고 다 저장
            chatHistoryRepository.save(new ChatHistory().builder()
                            .messageType((String)jsonObject.get("messageType"))
                            .chatRoomId((String)jsonObject.get("chatRoomId"))
                            .sender((Long)jsonObject.get("memberSeq"))
                            .senderNickName((String)jsonObject.get("nickName"))
                            .chatMessage((String)jsonObject.get("msg"))
                            .chatHistoryTime(chatHistoryTime)
                            .build()
            );
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        super.afterConnectionEstablished(session);
//        sessionMap.put(session.getId(), session);
//        JSONObject obj = new JSONObject();
//        obj.put("type", "getId");
//        obj.put("sessionId", session.getId());
//        session.sendMessage(new TextMessage(obj.toJSONString()));
//        System.out.println("client Conneted : " + session.getUri().toString());

        // 소켓 연결시 세션 발급
        super.afterConnectionEstablished(session);
        boolean roomExist = false;
        String url = session.getUri().toString();
        System.out.println("client Conneted session: " + session);
        System.out.println("client Conneted : " + url);

        String tempRoomId = url.split("/chat/")[1];
        int chatRoomIdx = roomListSessions.size(); // 사이즈 겸 현재 채팅방 인덱스

        if(chatRoomIdx > 0){
            for(int i = 0; i < chatRoomIdx; ++i){
                String chatRoomId = (String)roomListSessions.get(i).get("chatRoomId");
                System.out.println("afterConnectionEstablished: " + chatRoomId);
                
                if(chatRoomId.equals(tempRoomId)){
                    roomExist = true;
                    chatRoomIdx = i;
                    break;
                }
            }
        }

        if(roomExist){ // 이미 존재하는 방에 세션 추가
            HashMap<String, Object> chatRoom = roomListSessions.get(chatRoomIdx);
            chatRoom.put(session.getId(), session);
        }
        else{   // 새로운 채팅방 세션 등록
            HashMap<String, Object> newChatRoom = new HashMap<>();
            newChatRoom.put("chatRoomId", tempRoomId);
            newChatRoom.put(session.getId(), session);
            roomListSessions.add(newChatRoom);
        }

        System.out.println(roomListSessions);

        // 세션 등록이 끝나면 발급 받은 세션 ID 값의 메시지 발송
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageType", "getSession");    // 발신 메시지 타입
        jsonObject.put("sessionId", session.getId());   // sessionId
//        jsonObject.put("time", LocalDateTime.now().toString());   // localDateTime
//        System.out.println(LocalDateTime.now().toString());

        session.sendMessage(new TextMessage(jsonObject.toJSONString()));
        System.out.println("afterConnectionEstablished session 전송: ");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessionMap.remove(session.getId());
//        super.afterConnectionClosed(session, status);
        System.out.println("client Closed : " + session.getUri().toString());

        // 소켓 종료
        if(roomListSessions.size() > 0){ // 소켓 종료시 해당하는 세션 삭제
            for(int i = 0; i < roomListSessions.size(); ++i){
                roomListSessions.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
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
